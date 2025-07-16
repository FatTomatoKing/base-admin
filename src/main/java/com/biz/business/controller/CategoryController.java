package com.biz.business.controller;

import com.biz.business.model.CategoryManagement;
import com.biz.business.service.CategoryManagementService;
import com.biz.mvc.vo.ResponseResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zard
 * @since 2025/7/14
 */

@Tag(name = "业务示例接口类")
@RestController
@RequestMapping("/business/category/management")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoryController {

    private final CategoryManagementService categoryManagementService;


    @GetMapping("/get-by-parent-id")
    public ResponseResult<List<CategoryManagement>> getCategoryByParentId(@RequestParam Long parentId) {
        List<CategoryManagement> categories = categoryManagementService.selectByParentId(parentId);
        return ResponseResult.ofSuccess(categories);
    }

    @PostMapping("/create")
    public ResponseResult<Void> createCategory(
            @RequestBody CategoryManagement categoryManagement) {
        categoryManagementService.insert(categoryManagement);
        return ResponseResult.ofSuccess();
    }

    @PutMapping("/update")
    public ResponseResult<Void> updateCategory(
            @RequestBody  CategoryManagement categoryManagement) {
        categoryManagementService.update(categoryManagement);
        return ResponseResult.ofSuccess();
    }

    @DeleteMapping("/delete-by-id")
    public ResponseResult<Void> deleteCategoryById(@RequestParam Long id) {
        categoryManagementService.deleteByID(id);
        return ResponseResult.ofSuccess();
    }



}
