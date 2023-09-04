package com.wxsl.rosalind.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.wxsl.rosalind.mybatis.configuration.EnhancedMapper;
import com.wxsl.rosalind.mybatis.entity.OrderInfo;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

public interface OrderInfoMapper extends EnhancedMapper<OrderInfo> {

    default List<OrderInfo> findByUserIdAndIdIn(Long userId, Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        LambdaQueryWrapper<OrderInfo> wrapper = lambdaWrapper()
                .eq(OrderInfo::getUserId, userId)
                .in(OrderInfo::getId, ids);
        return this.selectList(wrapper);
    }

    default List<OrderInfo> findByTradeId(Long tradeId) {
        return this.selectList(lambdaWrapper().eq(OrderInfo::getTradeId, tradeId));
    }
}
