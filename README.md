# Y-Music 音乐平台项目

## 项目简介

本项目是基于 Spring Boot 框架开发的音乐平台系统，采用前后端分离架构。项目实现了完整的音乐平台功能，包括用户管理、音乐播放、歌单管理、歌手信息等模块，支持管理端和用户端双端操作，适合学习和二次开发。

前端地址：https://github.com/3323223659/YMusic-frontEnd

## 技术架构

- **后端框架**：Spring Boot 2.7.3
- **数据库**：MySQL 8.0
- **缓存**：Redis
- **ORM 框架**：MyBatis Plus
- **权限认证**：JWT
- **文件存储**：阿里云 OSS
- **API 文档**：Knife4j (Swagger)
- **容器化**：Docker + Docker Compose
- **反向代理**：Nginx

## 目录结构

```text
music-project/
├── music-common/              # 公共模块
│   └── src/main/java/com/music/
│       ├── properties/        # 配置属性类
│       ├── utils/            # 工具类（JWT等）
│       └── ...
├── music-pojo/               # 实体类模块
│   └── src/main/java/com/music/
│       ├── entity/           # 数据库实体类
│       ├── dto/              # 数据传输对象
│       ├── vo/               # 视图对象
│       └── ...
├── music-server/             # 服务端模块
│   └── src/main/
│       ├── java/com/music/
│       │   ├── controller/   # 控制器层
│       │   │   ├── client/   # 用户端接口
│       │   │   └── manage/   # 管理端接口
│       │   ├── service/      # 服务层
│       │   ├── mapper/       # 数据访问层
│       │   ├── config/       # 配置类
│       │   ├── interceptor/  # 拦截器
│       │   ├── handler/      # 异常处理器
│       │   └── aspect/       # 切面类
│       └── resources/
│           ├── mapper/       # MyBatis XML 映射文件
│           ├── sql/          # 数据库脚本
│           └── application.yml # 配置文件
├── Dockerfile                # Docker 镜像构建文件
├── docker-compose.yml        # Docker 编排文件
├── nginx.conf               # Nginx 配置文件
├── pom.xml                  # Maven 主配置文件
└── README.md                # 项目说明文档
```

## 功能模块

### 用户端功能
- 用户注册/登录/注销
- 个人信息管理
- 音乐播放
- 歌曲收藏
- 歌手信息查看
- 歌单浏览

### 管理端功能
- 管理员登录
- 用户管理
- 音乐管理
- 歌手管理
- 歌单管理
- 数据统计

## 使用说明

### 1. 环境准备

- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+
- Docker & Docker Compose（可选）

### 2. 数据库配置

1. 创建数据库 `music_project`
2. 执行 `music-server/src/main/resources/sql/` 目录下的 SQL 脚本
3. 修改 `application.yml` 中的数据库连接配置

### 3. 配置文件修改

修改 `music-server/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    druid:
      url: jdbc:mysql://localhost:3306/music_project?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: your_username
      password: your_password
  redis:
    host: localhost
    port: 6379
    password: your_redis_password

music:
  alioss:
    endpoint: your_oss_endpoint
    access-key-id: your_access_key_id
    access-key-secret: your_access_key_secret
    audio-bucket-name: your_audio_bucket
    image-bucket-name: your_image_bucket
```

### 4. 运行项目

#### 方式一：本地运行
```bash
# 1. 克隆项目
git clone <repository-url>

# 2. 进入项目目录
cd music-project

# 3. 编译打包
mvn clean package -DskipTests

# 4. 运行项目
java -jar music-server/target/music-server-0.0.1-SNAPSHOT.jar
```

#### 方式二：Docker 运行
```bash
# 1. 构建并启动服务
docker-compose up -d

# 2. 查看服务状态
docker-compose ps
```

### 5. 访问地址

- 应用服务：http://localhost:8080
- API 文档：http://localhost:8080/doc.html

### 6. 默认账户

**管理员账户**：
- 用户名：admin
- 密码：123456

**测试用户**：
- 用户名：yanmg
- 密码：hello

## API 接口

### 用户端接口
- `POST /music/client/user/login` - 用户登录
- `POST /music/client/user/register` - 用户注册
- `GET /music/client/singer/list` - 获取歌手列表
- `GET /music/client/singer/page` - 歌手分页查询

### 管理端接口
- `POST /music/manage/admin/login` - 管理员登录
- `GET /music/manage/user/page` - 用户分页查询
- `POST /music/manage/music/add` - 添加音乐
- `PUT /music/manage/music/update` - 更新音乐信息

## 部署说明

### Docker 部署

1. 确保 Docker 和 Docker Compose 已安装
2. 修改 `docker-compose.yml` 中的环境变量
3. 执行部署命令：
   ```bash
   docker-compose up -d
   ```

### 传统部署

1. 打包项目：`mvn clean package -DskipTests`
2. 上传 jar 包到服务器
3. 配置 Nginx 反向代理
4. 启动应用：`java -jar music-server-0.0.1-SNAPSHOT.jar`

## 开发说明

### 项目结构说明
- `music-common`：公共模块，包含工具类、配置类等
- `music-pojo`：实体类模块，包含 Entity、DTO、VO 等
- `music-server`：服务端模块，包含业务逻辑和接口

### 开发规范
- 使用 MyBatis Plus 进行数据库操作
- 使用 JWT 进行用户认证
- 使用 AOP 进行日志记录
- 使用全局异常处理器处理异常
- 使用 Redis 进行数据缓存

## 参考资料

- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [MyBatis Plus 官方文档](https://baomidou.com/)
- [Knife4j 官方文档](https://doc.xiaominfo.com/)
- [阿里云 OSS 文档](https://help.aliyun.com/product/31815.html)

## 免责声明

本项目仅用于学习和交流，禁止用于商业用途。如有侵权请联系删除。

