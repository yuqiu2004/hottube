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
                        `state` TINYINT NOT NULL DEFAULT 0 COMMENT '状态 0正常 1封禁 2注销',
                        `create_datetime` DATETIME NOT NULL COMMENT '创建时间',
                        `update_datetime` DATETIME DEFAULT NULL COMMENT '注销时间',
                        PRIMARY KEY (`uid`),
                        UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
