
create table brand_management
(
    id                  bigint auto_increment comment '主键' primary key,
    tmName              VARCHAR(64) NOT NULL COMMENT '品牌名称',
    logoUrl             VARCHAR(512) NOT NULL COMMENT '品牌链接',
    created             datetime DEFAULT CURRENT_TIMESTAMP,
    updated             datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) engine = innodb comment '品牌管理';
