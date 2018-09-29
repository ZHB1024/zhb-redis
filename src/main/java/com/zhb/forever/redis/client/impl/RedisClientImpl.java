package com.zhb.forever.redis.client.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhb.forever.redis.client.RedisClient;
import com.zhb.forever.redis.client.base.RedisTemplateBase;

public class RedisClientImpl extends RedisTemplateBase implements RedisClient {

    private Logger log = LoggerFactory.getLogger(RedisClientImpl.class);
    
    public void addRedis(String name,String age) {
        redisTemplateUtil.set(name, age);
        log.info(name + " : " + redisTemplateUtil.get(name));
    }
    
    public Object getRedis(String key){
        return redisTemplateUtil.get(key);
    }
    
    public void addList(String key,List<?> value){
        redisTemplateUtil.setList(key, value);
    }
    
    public List<?> getList(String key){
        return redisTemplateUtil.getList(key);
    }

    @Override
    public void addSet(String key, Set<?> value) {
        redisTemplateUtil.setSet(key, value);
    }

    @Override
    public Set<?> getSet(String key) {
        return redisTemplateUtil.getSet(key);
    }

}
