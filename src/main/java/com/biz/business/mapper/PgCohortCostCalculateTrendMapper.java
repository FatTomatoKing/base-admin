package com.biz.business.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.biz.business.pojo.entity.PgCohortCostCalculateTrend;
import com.biz.constant.DataSourceNames;

import com.biz.mp.mybatis.BaseMapperX;
import com.biz.mp.mybatis.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author user
* @description 针对表【cohort_cost_calculate_trend(同期广告详细数据)】的数据库操作Mapper
* @createDate 2024-07-01 15:12:37
* @Entity generator.domain.CohortCostCalculateTrend
*/
@Mapper
@DS(DataSourceNames.CDS_PGSQL)
public interface PgCohortCostCalculateTrendMapper extends BaseMapperX<PgCohortCostCalculateTrend> {

    default List<PgCohortCostCalculateTrend> selectListByCondition(Integer dates, Long cohort){
        LambdaQueryWrapperX<PgCohortCostCalculateTrend> queryWrapperX = build();
        queryWrapperX.eq(PgCohortCostCalculateTrend::getDates, dates);
        queryWrapperX.leIfPresent(PgCohortCostCalculateTrend::getCohort, cohort);
        return selectList(queryWrapperX);
    }

}
