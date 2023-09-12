-- ----------------------------
-- Table structure for order_info
-- ----------------------------
CREATE TABLE `order_info`
(
    `id`         bigint(20) unsigned NOT NULL,
    `user_id`    bigint(20)          NOT NULL,
    `trade_id`   bigint(20)          NOT NULL,
    `product_id` bigint(20)          NOT NULL,
    `payment`    decimal(19, 2)      NOT NULL,
    `status`     int                 NOT NULL,
    `version`    bigint(20)          NOT NULL,
    `quantity`   int(11)             NOT NULL,
    `created`    datetime            NOT NULL,
    `modified`   datetime            NOT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for product
-- ----------------------------
CREATE TABLE `product`
(
    `id`          bigint(20) unsigned NOT NULL,
    `name`        varchar(255)        NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `price`       decimal(19, 2)      NOT NULL,
    `stock`       int(11)             NOT NULL,
    `version`     bigint(20)          NOT NULL,
    `created`     datetime            NOT NULL,
    `modified`    datetime            NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_name` (`name`)
);

-- ----------------------------
-- Table structure for trade_info
-- ----------------------------
CREATE TABLE `trade_info`
(
    `id`       bigint(20) unsigned NOT NULL,
    `user_id`  bigint(255)         NOT NULL,
    `payment`  decimal(19, 2)      NOT NULL,
    `status`   int                 NOT NULL,
    `version`  bigint(20)          NOT NULL,
    `created`  datetime            NOT NULL,
    `modified` datetime            NOT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user_info`
(
    `id`       bigint(20) unsigned NOT NULL,
    `username` varchar(255)        NOT NULL,
    `password` varchar(255)        NOT NULL,
    `version`  bigint(20)          NOT NULL,
    `created`  datetime            NOT NULL,
    `modified` datetime            NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_name` (`username`)
);

