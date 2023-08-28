package com.wxsl.rosalind.mybatis.service;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.mybatis.command.TradeCreatedCommand;
import com.wxsl.rosalind.mybatis.dto.OrderInfoCreatedDto;
import com.wxsl.rosalind.mybatis.dto.OrderInfoDto;
import com.wxsl.rosalind.mybatis.dto.TradeInfoDto;
import com.wxsl.rosalind.mybatis.enumeration.TradeStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wxsl1997
 */
class TradeServiceTest extends BaseTest {

    public static final long USER_ID = 1L;

    @Resource
    TradeService tradeService;

    @Resource
    OrderInfoService orderInfoService;

    @Test
    public void create() {

        Long userId = USER_ID;

        TradeCreatedCommand command = new TradeCreatedCommand();
        int orderNum = 3;
        command.setOrders(IntStream.rangeClosed(1, orderNum)
                .mapToObj(num -> OrderInfoCreatedDto.builder()
                        .productId((long) num)
                        .payment(BigDecimal.valueOf(num))
                        .quantity(num)
                        .build())
                .collect(Collectors.toList()));

        Long tradeId = tradeService.create(userId, command);

        TradeInfoDto trade = tradeService.findByTradeId(tradeId);
        BigDecimal expectPayment = command.getOrders().stream().map(o -> o.getPayment().multiply(BigDecimal.valueOf(o.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);

        // inspect trade
        Assertions.assertEquals(expectPayment.doubleValue(), Objects.requireNonNull(trade).getPayment().doubleValue());
        Assertions.assertEquals(TradeStatusEnum.CREATED, trade.getStatus());

        // inspect order
        List<OrderInfoDto> orders = orderInfoService.findByTradeId(tradeId);
        Assertions.assertEquals(orderNum, orders.size());
    }
}