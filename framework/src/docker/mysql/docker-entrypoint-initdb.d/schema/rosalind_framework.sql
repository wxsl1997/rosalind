SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS rosalind_framework;

USE rosalind_framework;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(255)        NOT NULL,
    `password` varchar(255)        NOT NULL,
    `version`  bigint(20)          NOT NULL,
    `created`  datetime            NOT NULL,
    `modified` datetime            NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for trade_rate
-- ----------------------------
DROP TABLE IF EXISTS `trade_rate`;
CREATE TABLE `trade_rate`
(
    `oid`      bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `content`  varchar(255) DEFAULT NULL,
    `nick`     varchar(255) DEFAULT NULL,
    `reply`    varchar(255) DEFAULT NULL,
    `result`   varchar(255) DEFAULT NULL,
    `tid`      bigint(20)   DEFAULT NULL,
    `created`  datetime(6)  DEFAULT NULL,
    `modified` datetime(6)  DEFAULT NULL,
    `version`  bigint(20)   DEFAULT NULL,
    PRIMARY KEY (`oid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;