-- 1. 用户关系表（关注、拉黑）
CREATE TABLE user_relation (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               follower_id BIGINT NOT NULL,        -- 发起方（关注者）
                               followee_id BIGINT NOT NULL,        -- 被关注方
                               relation_type TINYINT NOT NULL,     -- 1=关注，2=拉黑
                               create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                               UNIQUE KEY uniq_relation (follower_id, followee_id, relation_type)
);


-- 2. 聊天消息表
CREATE TABLE chat_message (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              sender_id BIGINT NOT NULL,
                              receiver_id BIGINT NOT NULL,
                              content VARCHAR(1000) NOT NULL,     -- 文本消息内容（改为限长）
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);


-- 3. 点赞表（视频）
CREATE TABLE user_video_like (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                 user_id BIGINT NOT NULL,
                                 video_id BIGINT NOT NULL,
                                 status TINYINT DEFAULT 1,           -- 1=已点赞，0=取消
                                 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 UNIQUE KEY uniq_like (user_id, video_id)
);


-- 4. 收藏表（视频）
CREATE TABLE user_video_favorite (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     user_id BIGINT NOT NULL,
                                     video_id BIGINT NOT NULL,
                                     status TINYINT DEFAULT 1,           -- 1=收藏，0=取消
                                     create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                     update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     UNIQUE KEY uniq_favorite (user_id, video_id)
);


-- 5. 评论表（视频）
CREATE TABLE user_video_comment (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    user_id BIGINT NOT NULL,
                                    video_id BIGINT NOT NULL,
                                    parent_id BIGINT DEFAULT 0,         -- 父评论ID，0表示一级评论
                                    content VARCHAR(1000) NOT NULL,     -- 评论内容限长
                                    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
