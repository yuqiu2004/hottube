-- 1. 视频元数据表
CREATE TABLE video_metadata (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                title VARCHAR(255) NOT NULL,
                                description VARCHAR(1000),
                                cover_url VARCHAR(512),

                                original_url VARCHAR(512) DEFAULT NULL,
                                original_bitrate INT DEFAULT NULL,                 -- bps
                                original_exists TINYINT DEFAULT 0,                 -- 1存在，0不存在

                                p720_url VARCHAR(512) DEFAULT NULL,
                                p720_bitrate INT DEFAULT NULL,
                                p720_exists TINYINT DEFAULT 0,

                                p360_url VARCHAR(512) DEFAULT NULL,
                                p360_bitrate INT DEFAULT NULL,
                                p360_exists TINYINT DEFAULT 0,

                                duration_seconds BIGINT DEFAULT 0,
                                play_count BIGINT DEFAULT 0 COMMENT '播放量',
                                creator_id BIGINT NOT NULL,
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

-- insert data
INSERT INTO category (name) VALUES
                                ('电影'),
                                ('电视剧'),
                                ('综艺'),
                                ('动漫'),
                                ('纪录片'),
                                ('音乐'),
                                ('游戏'),
                                ('体育'),
                                ('新闻'),
                                ('教育'),
                                ('科技'),
                                ('生活'),
                                ('搞笑'),
                                ('时尚'),
                                ('旅游'),
                                ('汽车'),
                                ('母婴'),
                                ('美食'),
                                ('宠物'),
                                ('短剧');


-- 3. 视频-分类关系表（多对多）
CREATE TABLE video_category_relation (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         video_id BIGINT NOT NULL,
                                         category_id BIGINT NOT NULL,
                                         create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                         UNIQUE KEY uniq_video_category (video_id, category_id)
);
