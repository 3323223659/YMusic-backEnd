create table user
(
    id          bigint auto_increment comment '主键id'
        primary key,
    username    varchar(32) not null comment '用户账户',
    name        varchar(32) not null comment '用户昵称',
    password    varchar(64) not null comment '用户密码',
    sex         char        null comment '用户性别(男1,女2)',
    age         int         null comment '用户年龄',
    create_time datetime    null comment '创建时间',
    login_time  datetime    not null comment '最后登录时间',
    constraint idx_username
        unique (username)
)
    comment '用户信息' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.user (id, username, name, password, sex, age, create_time, login_time) VALUES (1, 'yanmg', '杨', '96e79218965eb72c92a549dd5a330112', '1', 19, '2024-07-17 21:25:17', '2024-07-18 00:10:25');
INSERT INTO music_project.user (id, username, name, password, sex, age, create_time, login_time) VALUES (4, 'li', 'li', 'e10adc3949ba59abbe56e057f20f883e', null, null, '2024-07-18 20:16:26', '2024-08-30 20:25:19');
INSERT INTO music_project.user (id, username, name, password, sex, age, create_time, login_time) VALUES (5, 'zhang', 'zhang', 'e10adc3949ba59abbe56e057f20f883e', null, null, '2024-07-18 20:16:36', '2024-08-08 00:25:13');
INSERT INTO music_project.user (id, username, name, password, sex, age, create_time, login_time) VALUES (6, 'wang', 'wang', 'e10adc3949ba59abbe56e057f20f883e', null, null, '2024-07-18 20:16:45', '2024-07-18 20:16:45');
INSERT INTO music_project.user (id, username, name, password, sex, age, create_time, login_time) VALUES (9, 'hang', 'hang', 'e10adc3949ba59abbe56e057f20f883e', '1', 20, '2024-08-26 15:35:30', '2024-09-01 11:02:45');
