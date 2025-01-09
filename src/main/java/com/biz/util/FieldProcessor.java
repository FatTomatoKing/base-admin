package com.biz.util;

import cn.hutool.core.util.ReflectUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class FieldProcessor {
    /**
     * 处理字段
     */
    public static <T> void process(T target, String fieldPrefix,
            int startIndex, int endIndex, Function<BigDecimal, BigDecimal> processor) {

        for (int i = startIndex; i <= endIndex; i++) {
            String fieldName = fieldPrefix + i;
            BigDecimal value = (BigDecimal) ReflectUtil.getFieldValue(target, fieldName);
            if (value != null) {
                ReflectUtil.setFieldValue(target, fieldName, processor.apply(value));
            }
        }
    }

    /**
     * 安全除法: 除数为null或0时返回0
     */
    public static BigDecimal safeDivide(BigDecimal value, BigDecimal divisor) {
        if (value == null || divisor == null || BigDecimal.ZERO.equals(divisor) || BigDecimal.ZERO.equals(value)) {
            return BigDecimal.ZERO;
        }
        return value.divide(divisor, 2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        CohortChannelRoi cohortChannelRoi = new CohortChannelRoi();
        cohortChannelRoi.setD0(BigDecimal.valueOf(5));
        process(cohortChannelRoi, "d", 0, 89, value -> safeDivide(value, BigDecimal.valueOf(2)));
        System.out.println(cohortChannelRoi);
        
    }

    @Data
    static class CohortChannelRoi {
        private BigDecimal d0 = BigDecimal.ZERO;
        private BigDecimal d1 = BigDecimal.ZERO;
        private BigDecimal d2 = BigDecimal.ZERO;
        private BigDecimal d3 = BigDecimal.ZERO;
        private BigDecimal d4 = BigDecimal.ZERO;
        private BigDecimal d5 = BigDecimal.ZERO;
        private BigDecimal d6 = BigDecimal.ZERO;
        private BigDecimal d7 = BigDecimal.ZERO;
        private BigDecimal d8 = BigDecimal.ZERO;
        private BigDecimal d9 = BigDecimal.ZERO;
        private BigDecimal d10 = BigDecimal.ZERO;
        private BigDecimal d11 = BigDecimal.ZERO;
        private BigDecimal d12 = BigDecimal.ZERO;
        private BigDecimal d13 = BigDecimal.ZERO;
        private BigDecimal d14 = BigDecimal.ZERO;
        private BigDecimal d15 = BigDecimal.ZERO;
        private BigDecimal d16 = BigDecimal.ZERO;
        private BigDecimal d17 = BigDecimal.ZERO;
        private BigDecimal d18 = BigDecimal.ZERO;
        private BigDecimal d19 = BigDecimal.ZERO;
        private BigDecimal d20 = BigDecimal.ZERO;
        private BigDecimal d21 = BigDecimal.ZERO;
        private BigDecimal d22 = BigDecimal.ZERO;
        private BigDecimal d23 = BigDecimal.ZERO;
        private BigDecimal d24 = BigDecimal.ZERO;
        private BigDecimal d25 = BigDecimal.ZERO;
        private BigDecimal d26 = BigDecimal.ZERO;
        private BigDecimal d27 = BigDecimal.ZERO;
        private BigDecimal d28 = BigDecimal.ZERO;
        private BigDecimal d29 = BigDecimal.ZERO;
        private BigDecimal d30 = BigDecimal.ZERO;
        private BigDecimal d31 = BigDecimal.ZERO;
        private BigDecimal d32 = BigDecimal.ZERO;
        private BigDecimal d33 = BigDecimal.ZERO;
        private BigDecimal d34 = BigDecimal.ZERO;
        private BigDecimal d35 = BigDecimal.ZERO;
        private BigDecimal d36 = BigDecimal.ZERO;
        private BigDecimal d37 = BigDecimal.ZERO;
        private BigDecimal d38 = BigDecimal.ZERO;
        private BigDecimal d39 = BigDecimal.ZERO;
        private BigDecimal d40 = BigDecimal.ZERO;
        private BigDecimal d41 = BigDecimal.ZERO;
        private BigDecimal d42 = BigDecimal.ZERO;
        private BigDecimal d43 = BigDecimal.ZERO;
        private BigDecimal d44 = BigDecimal.ZERO;
        private BigDecimal d45 = BigDecimal.ZERO;
        private BigDecimal d46 = BigDecimal.ZERO;
        private BigDecimal d47 = BigDecimal.ZERO;
        private BigDecimal d48 = BigDecimal.ZERO;
        private BigDecimal d49 = BigDecimal.ZERO;
        private BigDecimal d50 = BigDecimal.ZERO;
        private BigDecimal d51 = BigDecimal.ZERO;
        private BigDecimal d52 = BigDecimal.ZERO;
        private BigDecimal d53 = BigDecimal.ZERO;
        private BigDecimal d54 = BigDecimal.ZERO;
        private BigDecimal d55 = BigDecimal.ZERO;
        private BigDecimal d56 = BigDecimal.ZERO;
        private BigDecimal d57 = BigDecimal.ZERO;
        private BigDecimal d58 = BigDecimal.ZERO;
        private BigDecimal d59 = BigDecimal.ZERO;
        private BigDecimal d60 = BigDecimal.ZERO;
        private BigDecimal d61 = BigDecimal.ZERO;
        private BigDecimal d62 = BigDecimal.ZERO;
        private BigDecimal d63 = BigDecimal.ZERO;
        private BigDecimal d64 = BigDecimal.ZERO;
        private BigDecimal d65 = BigDecimal.ZERO;
        private BigDecimal d66 = BigDecimal.ZERO;
        private BigDecimal d67 = BigDecimal.ZERO;
        private BigDecimal d68 = BigDecimal.ZERO;
        private BigDecimal d69 = BigDecimal.ZERO;
        private BigDecimal d70 = BigDecimal.ZERO;
        private BigDecimal d71 = BigDecimal.ZERO;
        private BigDecimal d72 = BigDecimal.ZERO;
        private BigDecimal d73 = BigDecimal.ZERO;
        private BigDecimal d74 = BigDecimal.ZERO;
        private BigDecimal d75 = BigDecimal.ZERO;
        private BigDecimal d76 = BigDecimal.ZERO;
        private BigDecimal d77 = BigDecimal.ZERO;
        private BigDecimal d78 = BigDecimal.ZERO;
        private BigDecimal d79 = BigDecimal.ZERO;
        private BigDecimal d80 = BigDecimal.ZERO;
        private BigDecimal d81 = BigDecimal.ZERO;
        private BigDecimal d82 = BigDecimal.ZERO;
        private BigDecimal d83 = BigDecimal.ZERO;
        private BigDecimal d84 = BigDecimal.ZERO;
        private BigDecimal d85 = BigDecimal.ZERO;
        private BigDecimal d86 = BigDecimal.ZERO;
        private BigDecimal d87 = BigDecimal.ZERO;
        private BigDecimal d88 = BigDecimal.ZERO;
        private BigDecimal d89 = BigDecimal.ZERO;
    }
}
