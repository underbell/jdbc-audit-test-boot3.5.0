CREATE TABLE IF NOT EXISTS `user` (
    `email`         varchar(255) PRIMARY KEY,
    `name`          varchar(255) DEFAULT NULL,
    `created_at`    datetime     NOT NULL,
    `modified_at`   datetime
) COLLATE utf8mb4_general_ci;