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
    }
    
    public  String get(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }
    
    public  void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
    }
    
    public  void set(byte[] key,byte[] value) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.set(key, value);
    }
    
    public  byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }
    
    public  void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
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
        
    }
    
    public  List<String> getList(String key) {
        Jedis jedis = jedisPool.getResource();
        if (StringUtil.isNotBlank(key)) {
            return jedis.lrange(key, 0,-1);
        }
        return null;
    }
    
    public  void delList(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
    }
    
//map---------------------------------------------------------------
    public  void setMap(String key,Map<String, String> map) {
        Jedis jedis = jedisPool.getResource();
        jedis.hmset(key, map);
    }
    
    public  List<String> getMap(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hvals(key);
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
