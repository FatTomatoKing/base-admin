package com.biz.business.dao;


import com.biz.business.model.CategoryManagement;
import com.biz.mp.mybatis.BaseMapperX;
import com.biz.mp.mybatis.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author user
* @description 针对表【category_management(分类管理)】的数据库操作Mapper
* @createDate 2025-07-14 17:32:13
* @Entity com.biz.business.model.CategoryManagement
*/
@Mapper
public interface CategoryManagementMapper extends BaseMapperX<CategoryManagement> {

    default List<CategoryManagement> selectByParentId(Long parentId){
        LambdaQueryWrapperX<CategoryManagement> queryWrapperX = build();
        queryWrapperX.eqIfPresent(CategoryManagement::getParentId, parentId);
        return selectList(queryWrapperX);
    }
}
