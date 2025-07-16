package com.biz.business.service;

import com.biz.business.dao.CategoryManagementMapper;
import com.biz.business.model.CategoryManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zard
 * @since 2025/7/14
 */
@Service
@RequiredArgsConstructor
public class CategoryManagementService {
    private final CategoryManagementMapper categoryManagementMapper;

    public List<CategoryManagement> selectByParentId(Long parentId){
        return categoryManagementMapper.selectByParentId(parentId);
    }

    public void insert(CategoryManagement categoryManagement){
        categoryManagementMapper.insert(categoryManagement);
    }

    public void update(CategoryManagement categoryManagement){
        categoryManagementMapper.updateById(categoryManagement);
    }

    public void deleteByID(Long id){
        categoryManagementMapper.deleteById(id);
    }
}
