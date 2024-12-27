package com.biz.business.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.biz.business.pojo.entity.PgCohortCampaignRoiOrigin;
import com.biz.constant.DataSourceNames;

import com.biz.mp.mybatis.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
* @author user
* @description 针对表【cohort_campaign_roi_origin(广告系列同期roi分成中间值)】的数据库操作Mapper
* @createDate 2024-08-02 16:20:13
* @Entity generator.domain.CohortCampaignRoiOrigin
*/

@Mapper
@DS(DataSourceNames.CDS_PGSQL)
public interface CohortCampaignRoiOriginMapper extends BaseMapperX<PgCohortCampaignRoiOrigin> {



}
