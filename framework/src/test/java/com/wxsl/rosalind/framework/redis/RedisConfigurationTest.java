package com.wxsl.rosalind.framework.redis;

import com.wxsl.rosalind.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Disabled
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
        Assertions.assertNotNull(ping);
    }

    @Test
    void dump() {
        String key = "k1";
        String value = "v1";

        stringRedisTemplate.opsForValue().set(key, value);
        // source database execute dump
        byte[] source = stringRedisTemplate.dump(key);

        // target database execute restore
        //noinspection ConstantConditions
        stringRedisTemplate.restore(key, source, 0, TimeUnit.SECONDS, true);

        // 结果 检验
        Assertions.assertEquals(value, stringRedisTemplate.opsForValue().get(key));
    }
}
