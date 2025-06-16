package com.biz.business.dao;

import com.biz.business.model.BrandManagement;

import com.biz.mp.mybatis.BaseMapperX;
import com.biz.mp.mybatis.LambdaQueryWrapperX;
import com.biz.mp.mybatis.PageParam;
import com.biz.mp.mybatis.PageResult;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.ws.soap.MTOM;

/**
* @author lipeng
* @description 针对表【brand_management(品牌管理)】的数据库操作Mapper
* @createDate 2025-06-14 13:32:37
* @Entity com.biz.business.model.BrandManagement
*/
@Mapper
public interface BrandManagementMapper extends BaseMapperX<BrandManagement> {


    default PageResult<BrandManagement> listPage(PageParam pageParam, BrandManagement brandManagement){
        LambdaQueryWrapperX<BrandManagement> queryWrapperX = build();
        queryWrapperX.likeIfPresent(BrandManagement::getTmName, brandManagement.getTmName());
        queryWrapperX.likeIfPresent(BrandManagement::getLogoUrl, brandManagement.getLogoUrl());
        return selectPage(pageParam, queryWrapperX);
    }
}
