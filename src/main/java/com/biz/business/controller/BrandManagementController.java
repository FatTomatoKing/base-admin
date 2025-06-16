package com.biz.business.controller;


import com.biz.business.model.BrandManagement;
import com.biz.business.service.BrandManagementService;
import com.biz.mp.mybatis.PageParam;
import com.biz.mvc.vo.ResponseResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "业务示例接口类")
@RestController
@RequestMapping("/business/brand/management")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BrandManagementController {

    private final BrandManagementService brandManagementService;

    @GetMapping("/list-page")
    public ResponseResult<?> listPage(PageParam pageParam, BrandManagement brandManagement) {
        System.out.println(pageParam);
        return ResponseResult.ofSuccess(brandManagementService.listPage(pageParam, brandManagement));
    }

    @GetMapping("/get-by-id")
    public ResponseResult<?> getById(Long id) {
        return ResponseResult.ofSuccess(brandManagementService.getById(id));
    }

    /**
     * 更新品牌信息
     * PUT /brand
     * 请求体：BrandManagement对象（包含ID）
     */
    @PutMapping("/update-by-id")
    public ResponseResult<Boolean> updateBrand(@RequestBody BrandManagement brandManagement) {
        brandManagementService.updateById(brandManagement);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }

    /**
     * 添加新品牌
     * POST /brand
     * 请求体：BrandManagement对象（不包含ID，或ID为null）
     */
    @PostMapping("/save")
    public ResponseResult<Boolean> addBrand(@RequestBody BrandManagement brandManagement) {
        brandManagementService.add(brandManagement);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }

    /**
     * 根据ID获取品牌信息
     * GET /brand/{id}
     * 路径参数：id
     */
    @GetMapping("/get-brand-by-id")
    public ResponseResult<BrandManagement> getBrandById(Long id) {
        BrandManagement brand = brandManagementService.getById(id);
        return ResponseResult.ofSuccess(brand);
    }

    /**
     * 根据ID删除品牌
     * DELETE /brand/{id}
     * 路径参数：id
     */
    @DeleteMapping("/delete-by-id")
    public ResponseResult<Boolean> deleteBrandById(Long id) {
        brandManagementService.deleteById(id);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }
}
