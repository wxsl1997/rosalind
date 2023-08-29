package com.wxsl.rosalind.mybatis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import com.wxsl.rosalind.mybatis.converter.OrderInfoConverter;
import com.wxsl.rosalind.mybatis.dto.OrderInfoCreatedDto;
import com.wxsl.rosalind.mybatis.dto.OrderInfoDto;
import com.wxsl.rosalind.mybatis.entity.OrderInfo;
import com.wxsl.rosalind.mybatis.enumeration.OrderStatusEnum;
import com.wxsl.rosalind.mybatis.mapper.OrderInfoMapper;
import com.wxsl.rosalind.mybatis.util.StreamUtils;

/**
 * @author wxsl1997
 */
@Service
public class OrderInfoService {

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Resource
    OrderInfoConverter orderInfoConverter;

    @MybatisTransactional
    public void createOrder(Long userId, Long tradeId, List<OrderInfoCreatedDto> orders) {

        List<OrderInfo> rows = StreamUtils.map(orders, order -> OrderInfo.builder()
                .userId(userId)
                .tradeId(tradeId)
                .productId(order.getProductId())
                .payment(order.getPayment())
                .quantity(order.getQuantity())
                .status(OrderStatusEnum.CREATED)
                .build());

        orderInfoMapper.saveBatch(rows);
    }

    public List<OrderInfoDto> findByTradeId(Long tradeId) {
        List<OrderInfo> rows = orderInfoMapper.findByTradeId(tradeId);
        return StreamUtils.map(rows, orderInfoConverter::toOrderInfoDto);
    }
}
