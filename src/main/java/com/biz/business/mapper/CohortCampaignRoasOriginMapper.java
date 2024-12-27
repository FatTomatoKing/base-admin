package com.biz.business.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.biz.business.pojo.entity.PgCohortCampaignRoasOrigin;
import com.biz.constant.DataSourceNames;

import com.biz.mp.mybatis.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
* @author user
* @description 针对表【cohort_campaign_roas_origin(广告系列同期roas原始值)】的数据库操作Mapper
* @createDate 2024-06-25 18:23:34
* @Entity generator.domain.CohortCampaignRoasOrigin
*/
@Mapper
@DS(DataSourceNames.CDS_PGSQL)
public interface CohortCampaignRoasOriginMapper extends BaseMapperX<PgCohortCampaignRoasOrigin> {



}
