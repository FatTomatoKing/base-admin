package com.biz.business.service;

import com.biz.business.mapper.CohortCampaignRoasOriginMapper;
import com.biz.business.mapper.CohortCampaignRoiOriginMapper;
import com.biz.business.mapper.PgCohortChannelRoasOriginMapper;
import com.biz.business.mapper.PgCohortChannelRoiOriginMapper;
import com.biz.business.mapper.PgCohortCostCalculateTrendMapper;
import com.biz.business.pojo.entity.PgCohortCampaignRoasOrigin;
import com.biz.business.pojo.entity.PgCohortCampaignRoiOrigin;
import com.biz.business.pojo.entity.PgCohortChannelRoasOrigin;
import com.biz.business.pojo.entity.PgCohortChannelRoiOrigin;
import com.biz.business.pojo.entity.PgCohortCostCalculateTrend;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskInitCampaign {


    private final PgCohortCostCalculateTrendMapper pgCohortCostCalculateTrendMapper;

    private final CohortCampaignRoiOriginMapper cohortCampaignRoiOriginMapper;

    private final CohortCampaignRoasOriginMapper cohortCampaignRoasOriginMapper;


    @Data
    static class CohortCostCalculateKeyedVo {
        Integer dates;
        String channel;
        String campaignId;
        String source;
        int historyDaysRangeAdver;
        int historyDaysRangeFissile;
    }


    @PostConstruct
    public void init(){
        // 获取原始数据
        Integer dates = 20241123;
        Long cohort = 90L;
        List<PgCohortCostCalculateTrend> cohortCostCalculateTrendEntities = pgCohortCostCalculateTrendMapper.selectListByCondition(dates, cohort);

        Map<CohortCostCalculateKeyedVo, List<PgCohortCostCalculateTrend>> groupMap = new HashMap<>();
        for (PgCohortCostCalculateTrend cohortCostCalculateTrendEntity : cohortCostCalculateTrendEntities) {
            Integer bdates = cohortCostCalculateTrendEntity.getBdates();
            Integer rowDates = cohortCostCalculateTrendEntity.getDates();
            if (rowDates > bdates) {
                continue;
            }
            CohortCostCalculateKeyedVo cohortCostCalculateKeyedVo = new CohortCostCalculateKeyedVo();
            cohortCostCalculateKeyedVo.setDates(cohortCostCalculateTrendEntity.getDates());
            cohortCostCalculateKeyedVo.setChannel(cohortCostCalculateTrendEntity.getChannel());
            cohortCostCalculateKeyedVo.setCampaignId(cohortCostCalculateTrendEntity.getCampaignId());
            cohortCostCalculateKeyedVo.setSource(cohortCostCalculateTrendEntity.getSource());
            cohortCostCalculateKeyedVo.setHistoryDaysRangeAdver(cohortCostCalculateTrendEntity.getHistoryDaysRangeAdver());
            cohortCostCalculateKeyedVo.setHistoryDaysRangeFissile(cohortCostCalculateTrendEntity.getHistoryDaysRangeFissile());
            groupMap.computeIfAbsent(cohortCostCalculateKeyedVo, k-> new ArrayList<>())
                    .add(cohortCostCalculateTrendEntity);
        }

        List<PgCohortCampaignRoiOrigin> roiOrigins = new ArrayList<>();
        List<PgCohortCampaignRoasOrigin> roasOrigins = new ArrayList<>();
        groupMap.forEach((cohortCostCalculateKeyedVo, pgCohortCostCalculateTrends) -> {
            PgCohortCampaignRoiOrigin pgCohortChannelRoiOrigin = new PgCohortCampaignRoiOrigin();
            PgCohortCampaignRoasOrigin pgCohortChannelRoasOrigin = new PgCohortCampaignRoasOrigin();

            pgCohortChannelRoiOrigin.setDates(cohortCostCalculateKeyedVo.getDates())
                    .setChannel(cohortCostCalculateKeyedVo.getChannel()).setSource(cohortCostCalculateKeyedVo.getSource())
                    .setHistoryDaysRangeAdver(cohortCostCalculateKeyedVo.getHistoryDaysRangeAdver())
                    .setHistoryDaysRangeFissile(cohortCostCalculateKeyedVo.getHistoryDaysRangeFissile())
                    .setCampaignId(cohortCostCalculateKeyedVo.getCampaignId())
            ;

            long activeSum = pgCohortCostCalculateTrends.stream().mapToLong(PgCohortCostCalculateTrend::getActive).sum();
            pgCohortChannelRoiOrigin.setActive((int) activeSum);
            pgCohortChannelRoiOrigin.setCreated(new Date()).setUpdated(new Date());

            BeanUtils.copyProperties(pgCohortChannelRoiOrigin, pgCohortChannelRoasOrigin);
            // 设置roi的值
            setRoiData(dates, pgCohortChannelRoiOrigin, pgCohortCostCalculateTrends);

            setRoasData(dates, pgCohortChannelRoasOrigin, pgCohortCostCalculateTrends);
            roiOrigins.add(pgCohortChannelRoiOrigin);
            roasOrigins.add(pgCohortChannelRoasOrigin);
        });
        cohortCampaignRoiOriginMapper.insertBatch(roiOrigins);
        cohortCampaignRoasOriginMapper.insertBatch(roasOrigins);
    }

    private void setRoasData(Integer dates, PgCohortCampaignRoasOrigin pgCohortChannelRoasOrigin, List<PgCohortCostCalculateTrend> pgCohortCostCalculateTrends) {

        int dif = DateUtils.difDayByIntegerDayToNow(dates);
        for (int i = 0; i < dif; i++) {
            int finalI = i;
            List<PgCohortCostCalculateTrend> dataList = pgCohortCostCalculateTrends.stream()
                    .filter(cohortChannelDataDto -> cohortChannelDataDto.getCohort() <= finalI)
                    .collect(Collectors.toList());

            BigDecimal rechargeAll = dataList.stream().map(PgCohortCostCalculateTrend::getDayRecharge).reduce(BigDecimal.ZERO, BigDecimal::add);

            Long rechargeCount = dataList.stream().map(PgCohortCostCalculateTrend::getDayRechargeCount).reduce(0L, Long::sum);

            BigDecimal withdrawal = dataList.stream().map(PgCohortCostCalculateTrend::getDayWithdraw).reduce(BigDecimal.ZERO, BigDecimal::add);

            String fieldName = "d" + finalI;
            Field field = ReflectionUtils.findField(PgCohortCampaignRoasOrigin.class, fieldName);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, pgCohortChannelRoasOrigin, rechargeAll);
            pgCohortChannelRoasOrigin.setD0RechargeCount(Math.toIntExact(rechargeCount)).setD0Withdrawal(withdrawal);
        }
    }

    private void setRoiData(Integer dates, PgCohortCampaignRoiOrigin pgCohortChannelRoiOrigin, List<PgCohortCostCalculateTrend> pgCohortCostCalculateTrends) {
        int dif = DateUtils.difDayByIntegerDayToNow(dates);
        for (int i = 0; i < dif; i++) {
            int finalI = i;
            List<PgCohortCostCalculateTrend> dataList = pgCohortCostCalculateTrends.stream()
                    .filter(cohortChannelDataDto -> cohortChannelDataDto.getCohort() <= finalI)
                    .collect(Collectors.toList());


            BigDecimal rechargeAll = dataList.stream().map(PgCohortCostCalculateTrend::getDayRecharge).reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal withdrawalAll = dataList.stream().map(PgCohortCostCalculateTrend::getDayWithdraw).reduce(BigDecimal.ZERO, BigDecimal::add);

            Class<? extends PgCohortCampaignRoiOrigin> aClass = pgCohortChannelRoiOrigin.getClass();
            Field[] fields = aClass.getDeclaredFields();

            Arrays.stream(fields).forEach(field -> {
                field.setAccessible(true);
                String name = field.getName();
                String fieldName = "d" + finalI;
                if (fieldName.equals(name)){
                    // ROI=(对应新增日期在当前日期维度选择下的充值*（1-充值手续费）-对应新增日期在当前日期维度选择下的提现*（1+提现手续费）)*(1-离境费率）*分成/对应新增日期的成本
                    BigDecimal roi = rechargeAll.multiply(BigDecimal.ONE)
                            .subtract(withdrawalAll.multiply(BigDecimal.ONE))
                            .multiply(BigDecimal.ONE)
                            .multiply(BigDecimal.ONE);
                    try {
                        field.set(pgCohortChannelRoiOrigin, roi);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }


}
