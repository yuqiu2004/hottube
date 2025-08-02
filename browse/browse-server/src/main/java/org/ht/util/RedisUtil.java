package org.ht.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // 设置键值对，不设置过期时间
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 设置键值对，并设置过期时间（单位：秒）
    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    // 获取键对应的值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除键
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // 判断键是否存在
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // 设置键的过期时间（单位：秒）
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    // 获取键的剩余过期时间（单位：秒）
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}