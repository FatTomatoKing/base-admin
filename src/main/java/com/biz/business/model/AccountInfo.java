package com.biz.business.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 账号信息表
 * @TableName account_info
 */
@TableName(value ="account_info")
@Data
public class AccountInfo implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 环境网址
     */
    private String website;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 谷歌秘钥
     */
    private String secretKey;

    /**
     * 排序字段
     */
    private Integer orderNum;

    /**
     * 描述
     */
    private String description;
}