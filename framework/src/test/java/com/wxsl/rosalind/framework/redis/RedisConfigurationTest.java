package com.wxsl.rosalind.framework.redis;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Set;

class RedisConfigurationTest extends BaseTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Jedis jedis;

    @Test
    void stringRedisTemplate() {
        Set<String> keys = stringRedisTemplate.keys("*");
        Assertions.assertNotNull(keys);
    }

    @Test
    void jedis() {
        String ping = jedis.ping();
        Assertions.assertNull(ping);
    }
}
