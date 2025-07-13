DROP TABLE IF EXISTS `user_video_interact`;
CREATE TABLE `user_video_interact` (
                                       `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `uid` INT NOT NULL COMMENT '用户ID',
                                       `vid` INT NOT NULL COMMENT '视频ID',
                                       `liked` TINYINT NOT NULL DEFAULT 0 COMMENT '点赞 0否 1是',
                                       `disliked` TINYINT NOT NULL DEFAULT 0 COMMENT '点踩 0否 1是',
                                       `coin` TINYINT NOT NULL DEFAULT 0 COMMENT '投币数 0-2',
                                       `collected` TINYINT NOT NULL DEFAULT 0 COMMENT '是否收藏',
                                       `last_play_time` DATETIME DEFAULT NULL COMMENT '最后播放时间',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uid_vid` (`uid`, `vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与视频互动关系表';
