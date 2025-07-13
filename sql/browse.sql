DROP TABLE IF EXISTS `video_info`;
CREATE TABLE `video_info` (
                              `vid` INT NOT NULL COMMENT '视频ID',
                              `title` VARCHAR(80) NOT NULL COMMENT '标题',
                              `description` VARCHAR(2000) DEFAULT NULL COMMENT '简介',
                              `category_id` VARCHAR(20) NOT NULL COMMENT '分类ID',
                              `cover_url` VARCHAR(500) NOT NULL COMMENT '封面URL',
                              `upload_date` DATETIME NOT NULL COMMENT '上传时间',
                              `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态',
                              PRIMARY KEY (`vid`),
                              INDEX (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频展示信息';

DROP TABLE IF EXISTS `video_stats`;
CREATE TABLE `video_stats` (
                               `vid` INT NOT NULL COMMENT '视频ID',
                               `play_count` BIGINT NOT NULL DEFAULT 0 COMMENT '播放次数',
                               `like_count` BIGINT NOT NULL DEFAULT 0 COMMENT '点赞次数',
                               `favorite_count` BIGINT NOT NULL DEFAULT 0 COMMENT '收藏次数',
                               `comment_count` BIGINT NOT NULL DEFAULT 0 COMMENT '评论数',
                               `danmu_count` BIGINT NOT NULL DEFAULT 0 COMMENT '弹幕数',
                               `updated_at` DATETIME NOT NULL,
                               PRIMARY KEY (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='播放统计';

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                           `comment_id` INT NOT NULL AUTO_INCREMENT COMMENT '评论ID',
                           `vid` INT NOT NULL COMMENT '视频ID',
                           `uid` INT NOT NULL COMMENT '用户ID',
                           `content` VARCHAR(2000) NOT NULL COMMENT '评论内容',
                           `parent_comment_id` INT DEFAULT NULL COMMENT '父评论ID',
                           `create_time` DATETIME NOT NULL COMMENT '评论时间',
                           `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '软删除',
                           PRIMARY KEY (`comment_id`),
                           INDEX (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

DROP TABLE IF EXISTS `danmu`;
CREATE TABLE `danmu` (
                         `danmu_id` INT NOT NULL AUTO_INCREMENT COMMENT '弹幕ID',
                         `vid` INT NOT NULL COMMENT '视频ID',
                         `uid` INT NOT NULL COMMENT '用户ID',
                         `content` VARCHAR(500) NOT NULL COMMENT '弹幕内容',
                         `fontsize` TINYINT NOT NULL DEFAULT 25 COMMENT '字体大小',
                         `mode` TINYINT NOT NULL DEFAULT 1 COMMENT '弹幕模式',
                         `color` VARCHAR(7) NOT NULL DEFAULT '#FFFFFF' COMMENT '颜色',
                         `time_point` FLOAT NOT NULL COMMENT '视频时间点',
                         `create_date` DATETIME NOT NULL COMMENT '发送时间',
                         PRIMARY KEY (`danmu_id`),
                         INDEX (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='弹幕表';

DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
                            `fid` INT NOT NULL AUTO_INCREMENT COMMENT '收藏夹ID',
                            `uid` INT NOT NULL COMMENT '用户ID',
                            `title` VARCHAR(100) NOT NULL COMMENT '收藏夹标题',
                            `description` VARCHAR(200) DEFAULT NULL COMMENT '简介',
                            `cover_url` VARCHAR(500) DEFAULT NULL COMMENT '封面',
                            `visible` TINYINT NOT NULL DEFAULT 1 COMMENT '是否公开',
                            `created_at` DATETIME NOT NULL COMMENT '创建时间',
                            `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
                            PRIMARY KEY (`fid`),
                            INDEX (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏夹表';

DROP TABLE IF EXISTS `favorite_video`;
CREATE TABLE `favorite_video` (
                                  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `fid` INT NOT NULL COMMENT '收藏夹ID',
                                  `vid` INT NOT NULL COMMENT '视频ID',
                                  `added_time` DATETIME NOT NULL COMMENT '添加时间',
                                  `is_removed` TINYINT NOT NULL DEFAULT 0 COMMENT '是否移除',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `fid_vid` (`fid`, `vid`),
                                  INDEX (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏夹视频关系表';
