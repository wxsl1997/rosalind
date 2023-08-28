package com.wxsl.rosalind.spring.ioc.api;


import com.google.common.collect.Lists;
import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ImportBeanDefinitionRegistrarDemoTest extends BaseTest {

    @Test
    void testFindByUserIdAndTidIn() {

        TradeRepository api = applicationContext.getBean(TradeRepository.class);
        Long userId = 1L;
        List<Long> tids = Lists.newArrayList(1L, 2L, 3L);

        Integer actual = api.findByUserIdAndTidIn(userId, tids);
        Assertions.assertEquals(tids.size(), actual);
    }

    @Test
    void testFindByTidIn() {

        TradeRepository api = applicationContext.getBean(TradeRepository.class);
        List<Long> tids = Lists.newArrayList(1L, 2L, 3L);
        Integer serverId = 0;

        Integer actual = api.findByTidIn(serverId, tids);
        Assertions.assertEquals(tids.size(), actual);
    }
}