package com.biz.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.biz.constant.WebBizConstants;
import com.biz.rouyi.excel.annotation.ExcelEnumMessageCategory;
import com.biz.rouyi.excel.annotation.ExcelEnumMessageCode;
import lombok.Getter;

/**
 * @author suyh
 * @since 2024-09-03
 */
@Getter
@ExcelEnumMessageCategory(WebBizConstants.ENUM_EXCEL_FIELD_PREFIX_ENABLE_STATUS)
public enum EnableStatusEnums {
    ENABLE(1),
    DISABLE(0),
    ;

    @EnumValue
    @ExcelEnumMessageCode
    private final int code;

    EnableStatusEnums(int code) {
        this.code = code;
    }
}
