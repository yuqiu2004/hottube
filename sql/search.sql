DROP TABLE IF EXISTS `video_search_index`;
CREATE TABLE `video_search_index` (
                                      `vid` INT NOT NULL COMMENT '视频ID',
                                      `title` VARCHAR(255) NOT NULL COMMENT '视频标题',
                                      `tags` VARCHAR(500) DEFAULT NULL COMMENT '标签',
                                      `description` TEXT DEFAULT NULL COMMENT '描述',
                                      `category_id` VARCHAR(20) DEFAULT NULL COMMENT '分类ID',
                                      PRIMARY KEY (`vid`),
                                      FULLTEXT KEY `title_tags_desc` (`title`, `tags`, `description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频搜索索引表';

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `mc_id` VARCHAR(20) NOT NULL COMMENT '主分区ID',
                            `sc_id` VARCHAR(20) NOT NULL COMMENT '子分区ID',
                            `mc_name` VARCHAR(50) NOT NULL COMMENT '主分区名称',
                            `sc_name` VARCHAR(50) NOT NULL COMMENT '子分区名称',
                            `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
                            PRIMARY KEY (`mc_id`, `sc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频分类表';
