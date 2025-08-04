package org.ht.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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

    // 设置多个键值对，不设置过期时间
    public void setAll(List<String> keys, List<Object> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Keys and values size must be equal");
        }
        for (int i = 0; i < keys.size(); i++) {
            set(keys.get(i), values.get(i));
        }
    }

    // 设置多个键值对，并设置过期时间（单位：秒）
    public void setAll(List<String> keys, List<Object> values, long timeout) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Keys and values size must be equal");
        }
        for (int i = 0; i < keys.size(); i++) {
            set(keys.get(i), values.get(i), timeout);
        }
    }

    // 获取键对应的值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 获取多个键对应的值
    public List<Object> get(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    // 删除单个键
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // 删除多个键
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
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

    // 自增操作
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    // 自减操作
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // 判断值是否存在于列表中
    public Boolean isMemberOfList(String key, Object value) {
        return redisTemplate.opsForList().range(key, 0, -1).contains(value);
    }

    // 向列表中添加值
    public void addToList(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    // 从列表中移除值
    public void removeFromList(String key, Object value) {
        redisTemplate.opsForList().remove(key, 0, value);
    }

    // 获取列表中的所有值
    public List<Object> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // 向集合中添加值
    public void addSetValue(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void addSetValues(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    // 从集合中移除值
    public void removeSetValue(String key, Object value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    // 判断值是否存在于集合中
    public Boolean isMemberOfSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    // 获取集合中的所有值
    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    // 向有序集合中添加值
    public void addZSetValue(String key, double score, Object value) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    // 从有序集合中移除值
    public void removeZSetValue(String key, Object value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    // 获取有序集合中的所有值
    public Set<Object> getZSet(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

    // 获取有序集合中的值和分数
    public Set<ZSetOperations.TypedTuple<Object>> getZSetWithScore(String key) {
        return redisTemplate.opsForZSet().rangeWithScores(key, 0, -1);
    }
}