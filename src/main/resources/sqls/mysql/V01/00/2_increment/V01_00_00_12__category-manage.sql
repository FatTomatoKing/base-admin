
-- 创建分类管理
create table category_management
(
    id            bigint auto_increment comment '主键' primary key,
    category_name VARCHAR(64)  NOT NULL COMMENT '分类名称',
    parent_id     bigint NOT NULL COMMENT '父级id',
    created       datetime DEFAULT CURRENT_TIMESTAMP,
    updated       datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) comment '分类管理';
