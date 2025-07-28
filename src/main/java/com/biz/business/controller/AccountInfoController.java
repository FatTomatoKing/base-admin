package com.biz.business.controller;

import com.biz.business.model.AccountInfo;
import com.biz.business.service.AccountInfoService;
import com.biz.mp.mybatis.PageParam;
import com.biz.mvc.vo.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "账号信息管理")
@RestController
@RequestMapping("/business/account/info")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AccountInfoController {

    private final AccountInfoService accountInfoService;

    @Operation(summary = "分页查询账号信息")
    @GetMapping("/list-page")
    public ResponseResult<?> listPage(PageParam pageParam, AccountInfo accountInfo) {
        return ResponseResult.ofSuccess(accountInfoService.listPage(pageParam, accountInfo));
    }

    @Operation(summary = "查询所有账号信息")
    @GetMapping("/list-all")
    public ResponseResult<?> listAll() {
        return ResponseResult.ofSuccess(accountInfoService.listAll());
    }

    @Operation(summary = "根据ID获取账号信息")
    @GetMapping("/get-by-id")
    public ResponseResult<?> getById(Long id) {
        return ResponseResult.ofSuccess(accountInfoService.getById(id));
    }

    @Operation(summary = "新增账号信息")
    @PostMapping("/save")
    public ResponseResult<Boolean> add(@RequestBody AccountInfo accountInfo) {
        accountInfoService.add(accountInfo);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }

    @Operation(summary = "更新账号信息")
    @PutMapping("/update-by-id")
    public ResponseResult<Boolean> update(@RequestBody AccountInfo accountInfo) {
        accountInfoService.updateById(accountInfo);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }

    @Operation(summary = "删除账号信息")
    @DeleteMapping("/delete-by-id")
    public ResponseResult<Boolean> deleteById(Long id) {
        accountInfoService.deleteById(id);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }

    @Operation(summary = "批量删除账号信息")
    @DeleteMapping("/delete-batch")
    public ResponseResult<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        accountInfoService.deleteBatchByIds(ids);
        return ResponseResult.ofSuccess(Boolean.TRUE);
    }
} 