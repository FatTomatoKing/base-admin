package com.biz.mp.mysql.mapper.audit;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.biz.constant.DataSourceNames;
import com.biz.mp.mybatis.BaseMapperX;
import com.biz.mp.mysql.entity.audit.OperationRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suyh
 * @since 2024-09-02
 */
@Mapper
@DS(DataSourceNames.MYSQL_BASE)
public interface OperationRecordMapper extends BaseMapperX<OperationRecordEntity> {
}
