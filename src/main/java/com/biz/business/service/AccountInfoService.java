package com.biz.business.service;

import com.biz.business.dao.AccountInfoMapper;
import com.biz.business.model.AccountInfo;
import com.biz.mp.mybatis.LambdaQueryWrapperX;
import com.biz.mp.mybatis.PageParam;
import com.biz.mp.mybatis.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zard
 * @since 2025/7/25
 */
@Service
@RequiredArgsConstructor
public class AccountInfoService {
    private final AccountInfoMapper accountInfoMapper;

    /**
     * 分页查询账号信息
     * @param pageParam 分页参数
     * @param accountInfo 查询条件
     * @return 分页结果
     */
    public PageResult<AccountInfo> listPage(PageParam pageParam, AccountInfo accountInfo) {
        LambdaQueryWrapperX<AccountInfo> queryWrapperX = accountInfoMapper.build();
        queryWrapperX.likeIfPresent(AccountInfo::getEnvName, accountInfo.getEnvName())
                .likeIfPresent(AccountInfo::getWebsite, accountInfo.getWebsite())
                .likeIfPresent(AccountInfo::getUsername, accountInfo.getUsername())
                .orderByDesc(AccountInfo::getOrderNum);
        return accountInfoMapper.selectPage(pageParam, queryWrapperX);
    }

    /**
     * 查询所有账号信息
     * @return 账号信息列表
     */
    public List<AccountInfo> listAll() {
        return accountInfoMapper.selectList();
    }

    /**
     * 根据ID获取账号信息
     * @param id 账号ID
     * @return 账号信息
     */
    public AccountInfo getById(Long id) {
        return accountInfoMapper.selectById(id);
    }

    /**
     * 新增账号信息
     * @param accountInfo 账号信息
     */
    public void add(AccountInfo accountInfo) {
        accountInfoMapper.insert(accountInfo);
    }

    /**
     * 更新账号信息
     * @param accountInfo 账号信息
     */
    public void updateById(AccountInfo accountInfo) {
        accountInfoMapper.updateById(accountInfo);
    }

    /**
     * 删除账号信息
     * @param id 账号ID
     */
    public void deleteById(Long id) {
        accountInfoMapper.deleteById(id);
    }

    /**
     * 批量删除账号信息
     * @param ids 账号ID列表
     */
    public void deleteBatchByIds(List<Long> ids) {
        accountInfoMapper.deleteBatchIds(ids);
    }
}
