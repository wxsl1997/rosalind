package com.wxsl.rosalind.mybatis.mapper;

import com.wxsl.rosalind.base.BaseTest;
import com.wxsl.rosalind.mybatis.entity.OrderInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author wxsl1997
 */
class OrderInfoMapperTest extends BaseTest {

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Test
    void findByUserIdAndIdIn() {
        Long userId = 1L;
        List<Long> ids = LongStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<OrderInfo> orderInfos = orderInfoMapper.findByUserIdAndIdIn(userId, ids);
        Assertions.assertNotNull(orderInfos);
    }
}