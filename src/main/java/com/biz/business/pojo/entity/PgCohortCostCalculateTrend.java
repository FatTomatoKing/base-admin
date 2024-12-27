package com.biz.business.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 同期广告详细数据
 * @TableName cohort_cost_calculate_trend
 */
@TableName(value ="cohort_cost_calculate_trend")
@Data
public class PgCohortCostCalculateTrend  {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 注册日期
     */
    private Integer dates;

    /**
     * 行为日期
     */
    private Integer bdates;

    /**
     * 同期值
     */
    private Long cohort;

    /**
     * 项目
     */
    private String pn;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 广告key
     */
    private String key;

    /**
     * 投放平台
     */
    private String source;

    /**
     * 广告系列ID
     */
    private String campaignId;

    /**
     * 广告组ID
     */
    private String adGroupId;

    /**
     * 广告ID
     */
    private String adId;

    /**
     * 新增/活跃
     */
    private Long active;

    /**
     * 充值金额
     */
    private BigDecimal dayRecharge;

    /**
     * 充值人数
     */
    private Long dayRechargeCount;

    /**
     * 提现金额
     */
    private BigDecimal dayWithdraw;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改日期
     */
    private Date updated;


    /**
     * 【投放渠道】重复用户数统计时间范围：0-无重复，7-区间(0, -7]，30-区间(-7, -30]，60-区间(-30, -60]，99-区间(-60, -∞)
     */
    private int historyDaysRangeAdver;

    /**
     * 【裂变渠道】重复用户数统计时间范围：0-无重复，7-区间(0, -7]，30-区间(-7, -30]，60-区间(-30, -60]，99-区间(-60, -∞)
     */
    private int historyDaysRangeFissile;

    /**
     * 提现人数
     */
    private int dayWithdrawCount;

    /**
     * 首充用户人数(bdates 是为首充日期)
     */
    private int firstRechargeCount;

}