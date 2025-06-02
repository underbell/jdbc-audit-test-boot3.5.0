## Minimal Reproducible Example (MRE):

This repository provides a minimal example demonstrating the issue when using @EnableJdbcAuditing with a Persistable
entity whose isNew method relies on an audited field (createdAt).

Please run UserControllerTest to confirm the behavior.

To verify the regression, you can uncomment the dependencyManagement block in build.gradle to explicitly downgrade the
spring-data-bom version to 2024.1.6 and re-run the tests. The tests should pass with 2024.1.6.

```
/* if downgrade spring data then work well */
/*
dependencyManagement {
    imports {
        mavenBom "org.springframework.data:spring-data-bom:2024.1.6"
    }
}
*/
```

### Expected Behavior:

When repository.saveAll() is called with new entities (where isNew() returns true), Spring Data JDBC should perform
INSERT operations for all entities and correctly populate the @CreatedDate field via the auditing mechanism.

### Actual Behavior:

For new entities passed to repository.saveAll(), UPDATE operation is performed instead of an INSERT. This indicates a
potential issue with how auditing
callbacks or isNew detection is handled during batch saveAll operations in spring-data-bom:2025.0.0.

Relevant Code Snippets:
Entity isNew() implementation:

```
@CreatedDate
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
protected LocalDateTime createdAt;

@Override
public boolean isNew() {
  return createdAt == null;
}
```

JdbcAggregateTemplate's changeCreatorSelectorForSave method:

```
private <T> Function<T, RootAggregateChange<T>> changeCreatorSelectorForSave(T instance) {
    return context.getRequiredPersistentEntity(instance.getClass()).isNew(instance)
            ? entity -> createInsertChange(prepareVersionForInsert(entity))
            : entity -> createUpdateChange(prepareVersionForUpdate(entity));
}
```

The core of the issue appears to be that the result of isNew(instance) within changeCreatorSelectorForSave (or the
subsequent auditing logic) behaves differently for save() and saveAll() when createdAt is used for isNew detection.