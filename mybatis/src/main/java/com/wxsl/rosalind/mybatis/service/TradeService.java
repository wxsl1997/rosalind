package com.wxsl.rosalind.mybatis.service;

import com.wxsl.rosalind.mybatis.command.TradeCreatedCommand;
import com.wxsl.rosalind.mybatis.configuration.MybatisTransactional;
import com.wxsl.rosalind.mybatis.converter.TradeInfoConverter;
import com.wxsl.rosalind.mybatis.dto.TradeInfoDto;
import com.wxsl.rosalind.mybatis.entity.TradeInfo;
import com.wxsl.rosalind.mybatis.enumeration.TradeStatusEnum;
import com.wxsl.rosalind.mybatis.mapper.TradeInfoMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Service
public class TradeService {

    @Resource
    TradeInfoMapper tradeInfoMapper;

    @Resource
    OrderInfoService orderInfoService;

    @Resource
    TradeInfoConverter tradeInfoConverter;

    @MybatisTransactional
    public Long create(Long userId, TradeCreatedCommand command) {

        BigDecimal payment = command.getOrders().stream().map(o -> o.getPayment().multiply(BigDecimal.valueOf(o.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);

        TradeInfo trade = TradeInfo.builder()
                .userId(userId)
                .payment(payment)
                .status(TradeStatusEnum.CREATED)
                .build();
        // save trade
        tradeInfoMapper.insert(trade);

        // save order
        orderInfoService.createOrder(userId, trade.getId(), command.getOrders());

        return trade.getId();
    }

    public @Nullable TradeInfoDto findByTradeId(Long tradeId) {
        TradeInfo tradeInfo = tradeInfoMapper.selectById(tradeId);
        return tradeInfoConverter.toTradeInfoDto(tradeInfo);
    }
}
