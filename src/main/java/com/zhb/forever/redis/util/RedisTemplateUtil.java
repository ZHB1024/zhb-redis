package com.zhb.forever.redis.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTemplateUtil {

    private RedisTemplate redisTemplate;  
    
    public void set(String key, Object value) {  
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();  
        valueOperations.set(key, value,0);  
    }  
  
    public Object get(String key) {  
        return redisTemplate.opsForValue().get(key);  
    }  
  
    public void setList(String key, List<?> value) {  
        ListOperations<String, List<?>> listOperations = redisTemplate.opsForList();  
        listOperations.leftPushAll(key, value);  
    }  
  
    public List<?> getList(String key) { 
        Object result = redisTemplate.opsForList().leftPop(key);  
        if (null != result) {
            return (List<?>) result;  
        }else{
            return null;
        }
    }  
  
    public void setSet(String key, Set<?> value) {  
        SetOperations<String, Set<?>> setOperations = redisTemplate.opsForSet();  
        setOperations.add(key, value);  
    }  
  
    public Set<?> getSet(String key) {
        Object result = redisTemplate.opsForSet().members(key);
        if (null != result) {
            return (Set<?>)result;
        }else{
            return null;
        }
    }  
  
    public void setHash(String key, Map<String, ?> value) {  
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();  
        hashOperations.putAll(key, value);  
    }  
  
    public Object getHash(String key) {
        Object result = redisTemplate.opsForHash().entries(key);  
        if (null != result) {
            return (Map<String, ?>)result;
        }else{
            return null;
        }
    }  
  
    public void delete(String key) {  
        redisTemplate.delete(key);  
    }
    
    
    

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


}
