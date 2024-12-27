package com.biz.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.biz.constant.WebBizConstants;
import com.biz.rouyi.excel.annotation.ExcelEnumMessageCategory;
import com.biz.rouyi.excel.annotation.ExcelEnumMessageCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author suyh
 * @since 2024-09-03
 */
@Getter
@ExcelEnumMessageCategory(WebBizConstants.ENUM_EXCEL_FIELD_PREFIX_SHOW_STATUS)
public enum ShowStatusEnums {
    @Schema(description = "1-显示")
    SHOW(1),
    @Schema(description = "0-隐藏")
    HIDDEN(0),
    ;

    @EnumValue
    @ExcelEnumMessageCode
    private final int code;

    ShowStatusEnums(int code) {
        this.code = code;
    }
}
