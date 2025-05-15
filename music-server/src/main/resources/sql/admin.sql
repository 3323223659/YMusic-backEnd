create table admin
(
    id          bigint auto_increment comment '主键id'
        primary key,
    username    varchar(32) not null comment '用户名',
    name        varchar(32) not null comment '名称',
    password    varchar(64) not null,
    create_time datetime    null comment '创建时间',
    constraint idx_username
        unique (username)
)
    comment '管理员' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.admin (id, username, name, password, create_time) VALUES (1, 'admin', '管理员', 'e10adc3949ba59abbe56e057f20f883e', '2024-07-16 17:50:04');
