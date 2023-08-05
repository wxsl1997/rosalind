package com.wxsl.rosalind.jpa.service;

import com.wxsl.rosalind.jpa.configuration.JpaTransactional;
import com.wxsl.rosalind.jpa.dto.OrderInfoCreatedDto;
import com.wxsl.rosalind.jpa.dto.OrderInfoDto;
import com.wxsl.rosalind.jpa.enumeration.OrderStatusEnum;
import com.wxsl.rosalind.jpa.mapping.OrderInfoMapper;
import com.wxsl.rosalind.jpa.model.OrderInfo;
import com.wxsl.rosalind.jpa.repository.OrderInfoRepository;
import com.wxsl.rosalind.jpa.util.StreamUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxsl1997
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class OrderInfoService {

    OrderInfoRepository orderRepository;

    OrderInfoMapper orderInfoMapper;

    @JpaTransactional
    public void createOrder(Long userId, Long tradeId, List<OrderInfoCreatedDto> orders) {

        List<OrderInfo> rows = StreamUtils.map(orders, order -> OrderInfo.builder()
                .userId(userId)
                .tradeId(tradeId)
                .productId(order.getProductId())
                .payment(order.getPayment())
                .quantity(order.getQuantity())
                .status(OrderStatusEnum.CREATED)
                .build());

        orderRepository.saveAll(rows);
    }

    public List<OrderInfoDto> findByTradeId(Long tradeId) {
        List<OrderInfo> rows = orderRepository.findByTradeId(tradeId);
        return StreamUtils.map(rows, orderInfoMapper::toOrderInfoDto);
    }
}
