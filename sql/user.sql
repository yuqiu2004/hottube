-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `uid` INT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `username` VARCHAR(50) NOT NULL COMMENT '账号',
                        `password` VARCHAR(255) NOT NULL COMMENT '密码',
                        `nickname` VARCHAR(32) NOT NULL COMMENT '昵称',
                        `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
                        `background` VARCHAR(500) DEFAULT NULL COMMENT '主页背景图URL',
                        `gender` TINYINT NOT NULL DEFAULT 2 COMMENT '性别 0女 1男 2未知',
                        `description` VARCHAR(100) DEFAULT NULL COMMENT '个性签名',
                        `exp` INT NOT NULL DEFAULT 0 COMMENT '经验值',
                        `coin` DOUBLE NOT NULL DEFAULT 0 COMMENT '硬币数',
                        `vip` TINYINT NOT NULL DEFAULT 0 COMMENT '会员类型',
                        `state` TINYINT NOT NULL DEFAULT 0 COMMENT '状态 0正常 1封禁 2注销',
                        `auth` TINYINT NOT NULL DEFAULT 0 COMMENT '认证类型',
                        `auth_msg` VARCHAR(30) DEFAULT NULL COMMENT '认证说明',
                        `create_date` DATETIME NOT NULL COMMENT '创建时间',
                        `delete_date` DATETIME DEFAULT NULL COMMENT '注销时间',
                        PRIMARY KEY (`uid`),
                        UNIQUE KEY `username` (`username`),
                        UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 用户关注关系表
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
                            `follower_id` INT NOT NULL COMMENT '关注者ID',
                            `followed_id` INT NOT NULL COMMENT '被关注者ID',
                            `status` TINYINT NOT NULL DEFAULT 0 COMMENT '关注状态 0未关注 1已关注',
                            `created_date` DATETIME NOT NULL COMMENT '关注时间',
                            `deleted_date` DATETIME DEFAULT NULL COMMENT '取消关注时间',
                            PRIMARY KEY (`follower_id`, `followed_id`),
                            INDEX (`follower_id`),
                            INDEX (`followed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户关注关系表';

-- 聊天列表
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
                        `id` INT NOT NULL AUTO_INCREMENT COMMENT '聊天ID',
                        `user_id` INT NOT NULL COMMENT '用户ID',
                        `another_id` INT NOT NULL COMMENT '对方用户ID',
                        `is_deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除聊天',
                        `unread` INT NOT NULL DEFAULT 0 COMMENT '未读消息数',
                        `latest_time` DATETIME NOT NULL COMMENT '最近消息时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `from_to` (`user_id`, `another_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天列表';

-- 聊天记录
DROP TABLE IF EXISTS `chat_detailed`;
CREATE TABLE `chat_detailed` (
                                 `id` INT NOT NULL AUTO_INCREMENT COMMENT '消息ID',
                                 `user_id` INT NOT NULL COMMENT '发送者ID',
                                 `another_id` INT NOT NULL COMMENT '接收者ID',
                                 `content` VARCHAR(500) NOT NULL COMMENT '消息内容',
                                 `user_del` TINYINT NOT NULL DEFAULT 0 COMMENT '发送者删除标记',
                                 `another_del` TINYINT NOT NULL DEFAULT 0 COMMENT '接收者删除标记',
                                 `withdraw` TINYINT NOT NULL DEFAULT 0 COMMENT '是否撤回',
                                 `time` DATETIME NOT NULL COMMENT '发送时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天记录';
