package com.zhb.forever.redis.client.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhb.forever.framework.util.StringUtil;
import com.zhb.forever.redis.client.RedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientImpl implements RedisClient {

    private Logger logger = LoggerFactory.getLogger(RedisClientImpl.class);
    
    private JedisPool jedisPool;
    
  //单个对象---------------------------------------------------------------
    public  void set(String key,String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.set(key, value);
        closeResource(jedis);
    }
    
    public  String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        closeResource(jedis);
        return value;
    }
    
    public  void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        closeResource(jedis);
    }
    
    public  void set(byte[] key,byte[] value) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.set(key, value);
        closeResource(jedis);
    }
    
    public  byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(key);
        closeResource(jedis);
        return bytes;
    }
    
    public  void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        closeResource(jedis);
    }
    
    

//List---------------------------------------------------------------    
    public  void setList(String key,List<String> value) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        if (null != value) {
            for (String string : value) {
                jedis.lpush(key, string);
            }
        }
        closeResource(jedis);
    }
    
    public  List<String> getList(String key) {
        if (StringUtil.isNotBlank(key)) {
            Jedis jedis = jedisPool.getResource();
            List<String> values = jedis.lrange(key, 0,-1);
            closeResource(jedis);
            return values;
        }
        return null;
    }
    
    public  void delList(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        closeResource(jedis);
    }
    
//map---------------------------------------------------------------
    public  void setMap(String key,Map<String, String> map) {
        Jedis jedis = jedisPool.getResource();
        jedis.hmset(key, map);
        closeResource(jedis);
    }
    
    public  List<String> getMap(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.hvals(key);
        closeResource(jedis);
        return values;
    }

    
    
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    
    
    /***
     * 
     * 释放资源
     */
    public void closeResource(Jedis jedis) {
            if(jedis != null) {
                jedisPool.returnResource(jedis);
            }
    }
    
}
