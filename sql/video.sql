DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
                         `vid` INT NOT NULL AUTO_INCREMENT COMMENT '视频ID',
                         `uid` INT NOT NULL COMMENT '投稿用户ID',
                         `title` VARCHAR(80) NOT NULL COMMENT '视频标题',
                         `description` VARCHAR(2000) DEFAULT NULL COMMENT '视频简介',
                         `category_id` VARCHAR(20) NOT NULL COMMENT '视频分类ID',
                         `upload_date` DATETIME NOT NULL COMMENT '上传时间',
                         `status` TINYINT NOT NULL DEFAULT 0 COMMENT '视频状态',
                         PRIMARY KEY (`vid`),
                         INDEX (`uid`),
                         INDEX (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频基础表';

DROP TABLE IF EXISTS `video_transcode_task`;
CREATE TABLE `video_transcode_task` (
                                        `task_id` INT NOT NULL AUTO_INCREMENT COMMENT '转码任务ID',
                                        `vid` INT NOT NULL COMMENT '视频ID',
                                        `source_file_url` VARCHAR(500) NOT NULL COMMENT '原始文件URL',
                                        `status` TINYINT NOT NULL DEFAULT 0 COMMENT '任务状态',
                                        `progress` TINYINT NOT NULL DEFAULT 0 COMMENT '进度百分比',
                                        `error_msg` VARCHAR(500) DEFAULT NULL COMMENT '错误信息',
                                        `created_at` DATETIME NOT NULL,
                                        `updated_at` DATETIME NOT NULL,
                                        PRIMARY KEY (`task_id`),
                                        INDEX (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='转码任务表';

DROP TABLE IF EXISTS `video_representation`;
CREATE TABLE `video_representation` (
                                        `representation_id` INT NOT NULL AUTO_INCREMENT COMMENT '码率版本ID',
                                        `vid` INT NOT NULL COMMENT '视频ID',
                                        `resolution` VARCHAR(20) NOT NULL COMMENT '分辨率',
                                        `bitrate` INT NOT NULL COMMENT '码率(bps)',
                                        `codec` VARCHAR(50) NOT NULL COMMENT '编码格式',
                                        `manifest_url` VARCHAR(500) NOT NULL COMMENT '播放清单URL',
                                        `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态',
                                        `created_at` DATETIME NOT NULL,
                                        `updated_at` DATETIME NOT NULL,
                                        PRIMARY KEY (`representation_id`),
                                        INDEX (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='码率版本表';

DROP TABLE IF EXISTS `video_chunk`;
CREATE TABLE `video_chunk` (
                               `chunk_id` INT NOT NULL AUTO_INCREMENT COMMENT '切片ID',
                               `representation_id` INT NOT NULL COMMENT '码率版本ID',
                               `chunk_index` INT NOT NULL COMMENT '切片序号',
                               `chunk_url` VARCHAR(500) NOT NULL COMMENT '切片URL',
                               `chunk_duration` FLOAT NOT NULL COMMENT '切片时长',
                               PRIMARY KEY (`chunk_id`),
                               INDEX (`representation_id`),
                               UNIQUE KEY `representation_chunk_idx` (`representation_id`, `chunk_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频切片表';
