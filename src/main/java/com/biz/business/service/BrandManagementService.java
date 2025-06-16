package com.biz.business.service;


import com.biz.business.dao.BrandManagementMapper;
import com.biz.business.model.BrandManagement;
import com.biz.mp.mybatis.PageParam;
import com.biz.mp.mybatis.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author lipeng
* @description 针对表【brand_management(品牌管理)】的数据库操作Service实现
* @createDate 2025-06-14 13:32:37
*/
@Service
@RequiredArgsConstructor
public class BrandManagementService {

    private final BrandManagementMapper brandManagementMapper;


    public PageResult<BrandManagement> listPage(PageParam pageParam, BrandManagement brandManagement){
        return brandManagementMapper.listPage(pageParam,brandManagement);
    }

    public void updateById(BrandManagement brandManagement) {
        brandManagementMapper.updateById(brandManagement);
    }

    public void add(BrandManagement brandManagement) {
        brandManagementMapper.insert(brandManagement);
    }
    public BrandManagement getById(Long id) {
        return brandManagementMapper.selectById(id);
    }
    public void deleteById(Long id) {
        brandManagementMapper.deleteById(id);
    }
}
