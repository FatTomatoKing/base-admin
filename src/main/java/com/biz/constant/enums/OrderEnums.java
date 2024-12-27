package com.biz.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author suyh
 * @since 2024-09-04
 */
@Getter
public enum OrderEnums {
    @Schema(description = "升序")
    ascend("asc"),
    @Schema(description = "降序")
    descend("desc"),
    ;

    @EnumValue
    private final String code;

    OrderEnums(String code) {
        this.code = code;
    }
}
