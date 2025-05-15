create table singer
(
    id          bigint auto_increment comment '主键id'
        primary key,
    name        varchar(32)  not null comment '姓名',
    age         int          not null comment '年龄',
    sex         char         not null comment '性别(男1女2)',
    description varchar(255) not null comment '描述',
    image       varchar(255) not null comment '图片路径',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '修改时间'
)
    comment '歌手' collate = utf8mb4_0900_as_ci;

INSERT INTO music_project.singer (id, name, age, sex, description, image, create_time, update_time) VALUES (5, '周杰伦', 44, '1', '周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，祖籍福建省泉州市永春县，中国台湾流行乐男歌手、原创音乐人、演员、导演、编剧，毕业于淡江中学。', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/zjl.jpg', '2024-08-03 14:25:29', '2024-08-30 00:46:03');
INSERT INTO music_project.singer (id, name, age, sex, description, image, create_time, update_time) VALUES (7, '薛之谦', 41, '1', '薛之谦（Joker Xue），1983年7月17日出生于上海，毕业于格里昂酒店管理学院，华语流行乐男歌手、影视演员、音乐制作人。', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/48d19d42-d03e-4e06-8ce6-951e8c0ef947.jpeg', '2024-08-28 20:55:20', '2024-08-28 20:55:20');
INSERT INTO music_project.singer (id, name, age, sex, description, image, create_time, update_time) VALUES (9, 'xanemusic', 0, '1', 'Xanemusic is a viral phenomenon. He recently went viral with his remixes of Untouchable, Sure Thing and Mockingbird which made a lot of buzz on services.', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/24ff8774-ded2-4ec4-a0e6-b0d731be1440.jpg', '2024-09-01 01:58:08', '2024-09-01 01:58:08');
INSERT INTO music_project.singer (id, name, age, sex, description, image, create_time, update_time) VALUES (10, '疗愈音乐', 0, '2', '疗愈音律，轻音乐制作', 'https://ymusic-image.oss-cn-guangzhou.aliyuncs.com/ad4a8e39-0237-49bc-b618-dc4a206d6835.jpg', '2024-09-01 10:59:37', '2024-09-01 10:59:37');
