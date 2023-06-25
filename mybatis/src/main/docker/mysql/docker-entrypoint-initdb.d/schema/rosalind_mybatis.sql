SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS rosalind_mybatis;

USE rosalind_mybatis;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username`        varchar(255)        NOT NULL,
    `password`        varchar(255)        NOT NULL,
    `version`         bigint(20)          NOT NULL,
    `created`  datetime            NOT NULL,
    `modified` datetime            NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;



-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;

CREATE TABLE `country`
(
    `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255)        NOT NULL,
    `code` varchar(255)        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;