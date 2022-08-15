create table `user`
(
    `id`              bigint(20) unsigned not null auto_increment,
    `username`        varchar(255)        not null,
    `password`        varchar(255)        not null,
    `version`         bigint(20)          not null,
    `entity_created`  datetime            not null,
    `entity_modified` datetime            not null,
    primary key (`id`)
) engine = innodb;

create table `country`
(
    `id`   bigint(20) unsigned not null auto_increment,
    `name` varchar(255)        not null,
    `code` varchar(255)        not null,
    primary key (`id`)
) engine = innodb;