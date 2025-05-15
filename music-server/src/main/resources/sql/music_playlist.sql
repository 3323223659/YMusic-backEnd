create table music_playlist
(
    id          bigint auto_increment comment '主键id'
        primary key,
    music_id    bigint not null comment '歌曲id',
    playlist_id bigint not null comment '歌单id'
)
    comment '歌曲歌单关系表' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (27, 22, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (28, 23, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (29, 24, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (30, 25, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (31, 26, 1);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (32, 31, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (33, 33, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (34, 34, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (35, 35, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (36, 36, 1);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (37, 9, 11);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (38, 37, 14);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (39, 38, 14);
INSERT INTO music_project.music_playlist (id, music_id, playlist_id) VALUES (40, 36, 13);
