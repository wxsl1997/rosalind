package com.wxsl.rosalind.jpa.service;

import com.wxsl.rosalind.jpa.command.TradeCreatedCommand;
import com.wxsl.rosalind.jpa.configuration.JpaTransactional;
import com.wxsl.rosalind.jpa.dto.TradeInfoDto;
import com.wxsl.rosalind.jpa.enumeration.TradeStatusEnum;
import com.wxsl.rosalind.jpa.mapping.TradeInfoMapper;
import com.wxsl.rosalind.jpa.model.TradeInfo;
import com.wxsl.rosalind.jpa.repository.TradeInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wxsl1997
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class TradeService {

    TradeInfoRepository tradeInfoRepository;

    OrderInfoService orderInfoService;

    TradeInfoMapper tradeInfoMapper;

    @JpaTransactional
    public Long create(Long userId, TradeCreatedCommand command) {

        BigDecimal payment = command.getOrders().stream().map(o -> o.getPayment().multiply(BigDecimal.valueOf(o.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);

        TradeInfo trade = TradeInfo.builder()
                .userId(userId)
                .payment(payment)
                .status(TradeStatusEnum.CREATED)
                .build();
        // save trade
        tradeInfoRepository.save(trade);

        // save order
        orderInfoService.createOrder(userId, trade.getId(), command.getOrders());

        return trade.getId();
    }

    public TradeInfoDto findByTradeId(Long tradeId) {
        return tradeInfoRepository.findById(tradeId).map(tradeInfoMapper::toTradeInfoDto).orElse(null);
    }
}
