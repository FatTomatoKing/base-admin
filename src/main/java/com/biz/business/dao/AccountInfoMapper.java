package com.biz.business.dao;


import com.biz.business.model.AccountInfo;
import com.biz.mp.mybatis.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
* @author user
* @description 针对表【account_info(账号信息表)】的数据库操作Mapper
* @createDate 2025-07-25 15:33:08
* @Entity com.biz.domain.AccountInfo
*/
@Mapper
public interface AccountInfoMapper extends BaseMapperX<AccountInfo> {

}
