create table playlist
(
    id          bigint auto_increment comment '主键id'
        primary key,
    name        varchar(32)  not null comment '名称',
    image       varchar(255) not null comment '图片路径',
    description varchar(255) not null comment '描述',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '修改时间',
    constraint idx_name
        unique (name)
)
    comment '歌单' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.playlist (id, name, image, description, create_time, update_time) VALUES (1, '『游戏高燃BGM｜全程高能一燃到底』', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/01625ebb-0522-4f11-b761-e17415d9abdd.jpg', '打游戏怎么能少得了BGM呢? 是不是经常抱怨自己玩游戏技术不行？羡慕那些职业选手的技术？其实你和他们的距离就差一首BGM!快点跟随这动感的电子音乐，获得全场MVP吧！', '2024-08-03 10:42:01', '2024-08-25 16:00:23');
INSERT INTO music_project.playlist (id, name, image, description, create_time, update_time) VALUES (11, '华语R&B | 谁的嗓音俘虏你耳朵？', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/53f9667d-881f-4eab-9371-081b4a38cf04.jpg', ' R&B总是令人难以抗拒，时而舒服摇曳、时而深情细腻、时而慵懒安逸，华语优质作品当中，哪一个嗓音or旋律最让你动容?', '2024-08-30 21:12:59', '2024-08-30 21:12:59');
INSERT INTO music_project.playlist (id, name, image, description, create_time, update_time) VALUES (12, '粤语经典老歌 | 那些好听惊艳的广东歌', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/3fc0ed02-47a3-4b8d-a616-9b8e9802502b.jpg', ' 动人的广东歌 无论过去现在或未来 都将是我们珍贵的回忆。', '2024-08-30 21:16:18', '2024-08-30 21:16:18');
INSERT INTO music_project.playlist (id, name, image, description, create_time, update_time) VALUES (13, '说唱街区 | 动感节奏', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/14bc3825-cc4d-4df5-b4eb-ceb5560b4f37.jpg', '戴上耳机 享受嘻哈节拍与丝滑flow的完美融合', '2024-08-30 21:25:00', '2024-09-01 11:01:58');
INSERT INTO music_project.playlist (id, name, image, description, create_time, update_time) VALUES (14, '「轻音乐·安静」舒缓放松，缓解压力', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/2d3e0986-7324-45b7-99e4-33507d19ffcf.jpg', '缓解压力效果更为突出的是自然韵类音乐，比如潺潺的流水之音乐。一般来说柔和舒缓的音乐都可以让人平静、放松，这时的音乐应以舒缓柔美为宜，但是不要太低沉的音乐。总之，音乐的节奏应掌握在一定的范围之内，不要太慢，也不要太快。太慢了成了催眠曲，让人昏昏欲睡；太快了容易使人过度兴奋。', '2024-08-30 21:26:45', '2024-08-30 21:26:45');
