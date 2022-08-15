CREATE TABLE `user`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username`        varchar(255)        NOT NULL,
    `password`        varchar(255)        NOT NULL,
    `version`         bigint(20)          NOT NULL,
    `entity_created`  datetime            NOT NULL,
    `entity_modified` datetime            NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `country`
(
    `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255)        NOT NULL,
    `code` varchar(255)        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;