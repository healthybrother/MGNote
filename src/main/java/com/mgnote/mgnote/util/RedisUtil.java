package com.mgnote.mgnote.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Resource(name = "objectTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    private static final Long EXPIRE_TIME = 30L;
    private static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;
    public static final String USER_TOKEN = "user:token:?";

    public void writeCache(String key, Object value, Long expire, TimeUnit unit){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, expire, unit);
        log.info("write cache : key = {}, value = {}", key, value);
    }

    public void writeCache(String key, Object value){
        writeCache(key, value, EXPIRE_TIME, TIME_UNIT);
    }

    public Object readCache(String key){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object object = valueOperations.get(key);
        log.info("read cache : key = {}, value = {}", key, object);
        return object;
    }
}
