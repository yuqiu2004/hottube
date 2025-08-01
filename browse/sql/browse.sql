-- 1. 视频元数据表
CREATE TABLE video_metadata (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                title VARCHAR(255) NOT NULL,
                                description VARCHAR(1000),
                                cover_url VARCHAR(512),
                                video_url VARCHAR(512) NOT NULL,
                                duration_seconds INT DEFAULT 0, -- 视频时长（秒）
                                creator_id BIGINT NOT NULL,     -- 作者/UP主 ID
                                status TINYINT DEFAULT 1 COMMENT '1=发布, 0=待审, 2=下架',
                                create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. 分类表
CREATE TABLE category (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(64) NOT NULL UNIQUE,
                          create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 3. 视频-分类关系表（多对多）
CREATE TABLE video_category_relation (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         video_id BIGINT NOT NULL,
                                         category_id BIGINT NOT NULL,
                                         create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                         UNIQUE KEY uniq_video_category (video_id, category_id)
);
