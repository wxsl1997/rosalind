package com.wxsl.rosalind.jpa.repository;

import com.wxsl.rosalind.jpa.configuration.BaseJpaRepository;
import com.wxsl.rosalind.jpa.model.OrderInfo;

import java.util.List;

/**
 * @author wxsl1997
 */
public interface OrderInfoRepository extends BaseJpaRepository<OrderInfo, Long> {

    List<OrderInfo> findByTradeId(Long tradeId);
}
