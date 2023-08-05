# jpa

jpa

# 依赖服务


```sql

CREATE TABLE `user`
(
    `id`       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255)    NOT NULL,
    `password` VARCHAR(255)    NOT NULL,
    `version`  BIGINT          NOT NULL,
    `created`  datetime        NOT NULL,
    `modified` datetime        NOT NULL,
    UNIQUE KEY `uk_user_name` (`username`),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';


CREATE TABLE `product`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)    NOT NULL,
    `description` VARCHAR(255)    NOT NULL,
    `price`       DECIMAL(19, 2)  NOT NULL,
    `stock`       INT             NOT NULL,
    `version`     BIGINT          NOT NULL,
    `created`     datetime        NOT NULL,
    `modified`    datetime        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '商品表';


CREATE TABLE `trade_info`
(
    `id`       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`  BIGINT(255)     NOT NULL,
    `payment`  DECIMAL(19, 2)  NOT NULL,
    `status`   INT             NOT NULL,
    `version`  BIGINT          NOT NULL,
    `created`  datetime        NOT NULL,
    `modified` datetime        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '交易表';


CREATE TABLE `order_info`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT          NOT NULL,
    `trade_id`   BIGINT          NOT NULL,
    `product_id` BIGINT          NOT NULL,
    `payment`    DECIMAL(19, 2)  NOT NULL,
    `status`     INT             NOT NULL,
    `version`    BIGINT          NOT NULL,
    `quantity`   INT             NOT NULL,
    `created`    datetime        NOT NULL,
    `modified`   datetime        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '订单表';

```
