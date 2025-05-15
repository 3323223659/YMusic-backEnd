create table category
(
    id          bigint auto_increment comment '主键id'
        primary key,
    name        varchar(16) not null comment '名称',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间',
    constraint idx_name
        unique (name)
)
    comment '歌曲分类' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.category (id, name, create_time, update_time) VALUES (1, '流行', '2024-07-31 20:41:12', '2024-07-31 20:49:04');
INSERT INTO music_project.category (id, name, create_time, update_time) VALUES (2, '轻音乐', '2024-07-31 20:42:36', '2024-07-31 20:42:36');
INSERT INTO music_project.category (id, name, create_time, update_time) VALUES (3, '古风', '2024-07-31 20:43:12', '2024-07-31 20:49:25');
INSERT INTO music_project.category (id, name, create_time, update_time) VALUES (4, '说唱', '2024-07-31 20:43:35', '2024-07-31 20:48:54');
