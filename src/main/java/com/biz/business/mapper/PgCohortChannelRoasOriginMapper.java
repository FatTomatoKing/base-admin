package com.biz.business.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;

import com.biz.business.pojo.entity.PgCohortChannelRoasOrigin;
import com.biz.constant.DataSourceNames;
import com.biz.mp.mybatis.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
* @author user
* @description 针对表【cohort_channel_roas_origin(渠道同期roas原始值)】的数据库操作Mapper
* @createDate 2024-07-01 19:15:59
* @Entity generator.domain.CohortChannelRoasOrigin
*/
@Mapper
@DS(DataSourceNames.CDS_PGSQL)
public interface PgCohortChannelRoasOriginMapper extends BaseMapperX<PgCohortChannelRoasOrigin> {
}
