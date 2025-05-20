# 音乐项目管理系统

## 项目介绍

这是一个基于 SpringBoot 开发的音乐项目管理系统，实现了音乐资源管理、用户管理、权限控制等功能。

## 技术栈

- 后端框架：SpringBoot 2.7.3
- 数据库：MySQL 5.7
- 缓存：Redis 6.2.6
- ORM 框架：MyBatis-Plus 3.4.1
- 项目管理工具：Maven
- API 文档：Knife4j 3.0.2
- 其他工具：Lombok、Fastjson 等

## 项目结构

```
music-project
├── music-common  // 公共模块
├── music-pojo    // 实体类模块
└── music-server  // 服务器模块（主模块）
```

### 安装说明

```HTML
pull该项目
将资源目录下sql中的文件导入自己的数据库中
检查yml文件配置合适后即可以启动项目
```

### 使用说明

```HTML
前端代码地址：https://github.com/3323223659/YMusic-frontEnd
阿里云oss密钥自己申请进行替换
注意redis要启动，端口什么的配置文件自行配置
启动后的api文档（swagger）：http://localhost:8080/doc.html#/home
```
