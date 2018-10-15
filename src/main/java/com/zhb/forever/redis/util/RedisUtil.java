package com.zhb.forever.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    //服务器IP地址
    private static final String ADDR = "127.0.0.1";
    
    private static final int PORT = 6379;
    
    private static final String AUTH = "123456";
    
    //连接实例的最大连接数
    private static final int MAX_ACTIVE = 1024;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static final int MAX_IDLE = 200;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static final int MAX_WAIT = 10000;
    
    //连接超时的时间　　
    private static final int TIMEOUT = 10000;
    
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static final boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool = null;
    
    private static Jedis jedis = null;

    /**
     * 初始化Redis连接池
     */

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     */

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                if (null != jedis) {
                 return  jedis;   
                }else {
                    jedis = jedisPool.getResource();
                    return jedis;
                }
            } 
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 
     * 释放资源
     */
    public static void returnResource(final Jedis jedis) {
            if(jedis != null) {
                jedisPool.returnResource(jedis);
            }
        
    }

}
