package com.wxsl.rosalind.framework.jdbc.dao;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.framework.jdbc.model.TradeRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("TradeRateDao")
class TradeRateDaoTest extends BaseTest {

    @Test
    @DisplayName("listTradeRate")
    void listTradeRate() {
        TradeRateDao tradeRateDao = applicationContext().getBean("tradeRateDao", TradeRateDao.class);
        List<TradeRate> tradeRates = tradeRateDao.listTradeRate();
        Assertions.assertNotNull(tradeRates);
    }

    @Test
    @DisplayName("findByTid")
    void findByTid() {
        TradeRateDao tradeRateDao = applicationContext().getBean("tradeRateDao", TradeRateDao.class);
        TradeRate tradeRate = tradeRateDao.findByTid(null);
        Assertions.assertNull(tradeRate);
    }
}
