package com.biz.business.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;

import com.biz.business.pojo.entity.PgCohortChannelRoiOrigin;
import com.biz.constant.DataSourceNames;


import com.biz.mp.mybatis.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
* @author user
* @description 针对表【cohort_channel_roi_origin(渠道同期roi分成中间值)】的数据库操作Mapper
* @createDate 2024-07-01 18:47:59
* @Entity generator.domain.CohortChannelRoiOrigin
*/
@Mapper
@DS(DataSourceNames.CDS_PGSQL)
public interface PgCohortChannelRoiOriginMapper extends BaseMapperX<PgCohortChannelRoiOrigin> {

}
