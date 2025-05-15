create table music
(
    id          bigint auto_increment comment '主键id'
        primary key,
    name        varchar(32)      not null comment '歌曲名称',
    singer_id   bigint default 0 not null comment '演唱歌手id',
    category_id bigint default 0 not null comment '分类id',
    path        varchar(255)     not null comment '音乐存储路径',
    create_time datetime         null comment '创建时间',
    update_time datetime         null comment '修改时间'
)
    comment '歌曲' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (9, '青花瓷', 5, 3, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/3458986373.mp3', '2024-08-08 01:19:09', '2024-08-25 09:27:02');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (22, '我是如此相信', 5, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/756da268-5850-40d7-b944-6a6e7ef2b0c1.mp3', '2024-08-28 16:39:46', '2024-08-28 16:39:46');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (23, '说好的幸福呢', 5, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/47e82e8c-f603-450f-8bd5-7c9d5bc10878.mp3', '2024-08-28 16:56:55', '2024-08-28 16:56:55');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (24, '听妈妈的话', 5, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/5d197ef1-6363-4d10-8aac-963f584853d2.mp3', '2024-08-28 16:57:17', '2024-08-28 16:57:17');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (25, '稻香', 5, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/f0a53585-7f78-4f6d-ad96-709e64debfcf.mp3', '2024-08-28 16:57:33', '2024-08-28 16:57:33');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (26, '东风破', 5, 3, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/3b3dce62-76fb-465a-bbdb-e92097f794f7.mp3', '2024-08-28 17:08:10', '2024-08-28 17:08:10');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (31, '晴天', 5, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/9505f32a-5c4d-474b-8560-05e943580187.mp3', '2024-08-28 17:21:40', '2024-08-28 17:21:40');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (33, '演员', 7, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/0c1402e6-dcae-42c8-8ac2-98943a727924.mp3', '2024-09-01 02:02:35', '2024-09-01 02:02:35');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (34, '天外来物', 7, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/3980f06f-d767-4432-927b-c6306abea2b9.mp3', '2024-09-01 02:03:09', '2024-09-01 02:03:09');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (35, '动物世界', 7, 1, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/49d50822-5fb8-4d7f-a3ce-b78982820720.mp3', '2024-09-01 02:03:31', '2024-09-01 02:03:31');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (36, 'Moth To A Flame', 9, 4, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/d202c611-338b-4bc8-8708-723294c41999.mp3', '2024-09-01 02:32:34', '2024-09-01 02:32:34');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (37, '高质量睡眠', 10, 2, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/01817642-54d3-48f6-a0d9-86f3c09322c2.mp3', '2024-09-01 11:00:30', '2024-09-01 11:00:30');
INSERT INTO music_project.music (id, name, singer_id, category_id, path, create_time, update_time) VALUES (38, '深度放松大脑', 10, 2, 'https://ymusic-audio.oss-cn-guangzhou.aliyuncs.com/2be446ae-a0ee-422a-b3fd-b76998b46fae.mp3', '2024-09-01 11:01:06', '2024-09-01 11:01:06');
