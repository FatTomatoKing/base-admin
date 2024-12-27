package com.biz.business.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 渠道同期roas原始值
 * @TableName cohort_channel_roas_origin
 */
@TableName(value ="cohort_channel_roas_origin")
@Data
public class PgCohortChannelRoasOrigin implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 自然天日期
     */
    private Integer dates;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 投放平台
     */
    private String source;

    /**
     * 花费
     */
    private BigDecimal cost;

    /**
     * 花费(INR)
     */
    private BigDecimal costInr;

    /**
     * 新增人数
     */
    private Integer active;

    /**
     * 新增成本(cost/active)
     */
    private BigDecimal cpi;

    /**
     * 同期d0roas充值
     */
    private BigDecimal d0;

    /**
     * 同期d1roas充值
     */
    private BigDecimal d1;

    /**
     * 同期d2roas充值
     */
    private BigDecimal d2;

    /**
     *
     */
    private BigDecimal d3;

    /**
     *
     */
    private BigDecimal d4;

    /**
     *
     */
    private BigDecimal d5;

    /**
     *
     */
    private BigDecimal d6;

    /**
     *
     */
    private BigDecimal d7;

    /**
     *
     */
    private BigDecimal d8;

    /**
     *
     */
    private BigDecimal d9;

    /**
     *
     */
    private BigDecimal d10;

    /**
     *
     */
    private BigDecimal d11;

    /**
     *
     */
    private BigDecimal d12;

    /**
     *
     */
    private BigDecimal d13;

    /**
     *
     */
    private BigDecimal d14;

    /**
     *
     */
    private BigDecimal d15;

    /**
     *
     */
    private BigDecimal d16;

    /**
     *
     */
    private BigDecimal d17;

    /**
     *
     */
    private BigDecimal d18;

    /**
     *
     */
    private BigDecimal d19;

    /**
     *
     */
    private BigDecimal d20;

    /**
     *
     */
    private BigDecimal d21;

    /**
     *
     */
    private BigDecimal d22;

    /**
     *
     */
    private BigDecimal d23;

    /**
     *
     */
    private BigDecimal d24;

    /**
     *
     */
    private BigDecimal d25;

    /**
     *
     */
    private BigDecimal d26;

    /**
     *
     */
    private BigDecimal d27;

    /**
     *
     */
    private BigDecimal d28;

    /**
     *
     */
    private BigDecimal d29;

    /**
     *
     */
    private BigDecimal d30;

    /**
     *
     */
    private BigDecimal d31;

    /**
     *
     */
    private BigDecimal d32;

    /**
     *
     */
    private BigDecimal d33;

    /**
     *
     */
    private BigDecimal d34;

    /**
     *
     */
    private BigDecimal d35;

    /**
     *
     */
    private BigDecimal d36;

    /**
     *
     */
    private BigDecimal d37;

    /**
     *
     */
    private BigDecimal d38;

    /**
     *
     */
    private BigDecimal d39;

    /**
     *
     */
    private BigDecimal d40;

    /**
     *
     */
    private BigDecimal d41;

    /**
     *
     */
    private BigDecimal d42;

    /**
     *
     */
    private BigDecimal d43;

    /**
     *
     */
    private BigDecimal d44;

    /**
     *
     */
    private BigDecimal d45;

    /**
     *
     */
    private BigDecimal d46;

    /**
     *
     */
    private BigDecimal d47;

    /**
     *
     */
    private BigDecimal d48;

    /**
     *
     */
    private BigDecimal d49;

    /**
     *
     */
    private BigDecimal d50;

    /**
     *
     */
    private BigDecimal d51;

    /**
     *
     */
    private BigDecimal d52;

    /**
     *
     */
    private BigDecimal d53;

    /**
     *
     */
    private BigDecimal d54;

    /**
     *
     */
    private BigDecimal d55;

    /**
     *
     */
    private BigDecimal d56;

    /**
     *
     */
    private BigDecimal d57;

    /**
     *
     */
    private BigDecimal d58;

    /**
     *
     */
    private BigDecimal d59;

    /**
     *
     */
    private BigDecimal d60;

    /**
     *
     */
    private BigDecimal d61;

    /**
     *
     */
    private BigDecimal d62;

    /**
     *
     */
    private BigDecimal d63;

    /**
     *
     */
    private BigDecimal d64;

    /**
     *
     */
    private BigDecimal d65;

    /**
     *
     */
    private BigDecimal d66;

    /**
     *
     */
    private BigDecimal d67;

    /**
     *
     */
    private BigDecimal d68;

    /**
     *
     */
    private BigDecimal d69;

    /**
     *
     */
    private BigDecimal d70;

    /**
     *
     */
    private BigDecimal d71;

    /**
     *
     */
    private BigDecimal d72;

    /**
     *
     */
    private BigDecimal d73;

    /**
     *
     */
    private BigDecimal d74;

    /**
     *
     */
    private BigDecimal d75;

    /**
     *
     */
    private BigDecimal d76;

    /**
     *
     */
    private BigDecimal d77;

    /**
     *
     */
    private BigDecimal d78;

    /**
     *
     */
    private BigDecimal d79;

    /**
     *
     */
    private BigDecimal d80;

    /**
     *
     */
    private BigDecimal d81;

    /**
     *
     */
    private BigDecimal d82;

    /**
     *
     */
    private BigDecimal d83;

    /**
     *
     */
    private BigDecimal d84;

    /**
     *
     */
    private BigDecimal d85;

    /**
     *
     */
    private BigDecimal d86;

    /**
     *
     */
    private BigDecimal d87;

    /**
     *
     */
    private BigDecimal d88;

    /**
     *
     */
    private BigDecimal d89;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 首日充值人数
     */
    private Integer d0RechargeCount;

    /**
     * 首日提现额度
     */
    private BigDecimal d0Withdrawal;

    /**
     * 【投放渠道】重复用户数统计时间范围：0-无重复，7-区间(0, -7]，30-区间(-7, -30]，60-区间(-30, -60]，99-区间(-60, -∞)
     */
    private Integer historyDaysRangeAdver;

    /**
     * 【裂变渠道】重复用户数统计时间范围：0-无重复，7-区间(0, -7]，30-区间(-7, -30]，60-区间(-30, -60]，99-区间(-60, -∞)
     */
    private Integer historyDaysRangeFissile;

}