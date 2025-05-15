create table user_music
(
    id       bigint auto_increment comment '主键id'
        primary key,
    user_id  bigint not null comment '用户id',
    music_id bigint not null comment '歌曲id'
)
    comment '用户收藏歌曲关系表' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.user_music (id, user_id, music_id) VALUES (2, 5, 3);
INSERT INTO music_project.user_music (id, user_id, music_id) VALUES (4, 9, 9);
INSERT INTO music_project.user_music (id, user_id, music_id) VALUES (5, 9, 23);
INSERT INTO music_project.user_music (id, user_id, music_id) VALUES (7, 9, 24);
INSERT INTO music_project.user_music (id, user_id, music_id) VALUES (9, 9, 25);
