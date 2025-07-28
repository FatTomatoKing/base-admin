

-- 账号信息表
CREATE TABLE account_info
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    env_name    VARCHAR(64) NOT NULL COMMENT '环境名称',
    website     VARCHAR(512) DEFAULT NULL COMMENT '环境网址',
    username    VARCHAR(255) DEFAULT NULL COMMENT '用户名',
    password    VARCHAR(255) DEFAULT NULL COMMENT '密码',
    secret_key  VARCHAR(64)  DEFAULT NULL COMMENT '谷歌秘钥',
    order_num   INT          DEFAULT NULL COMMENT '排序字段',
    description VARCHAR(255) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账号信息表';