package com.zhb.forever.redis.util;

import java.util.List;
import java.util.Map;

import com.zhb.forever.framework.util.StringUtil;

public class RedisImplUtil {
    
//单个对象---------------------------------------------------------------
    public static void set(String key,String value) {
        RedisUtil.getJedis().del(key);
        RedisUtil.getJedis().set(key, value);
    }
    
    public static String get(String key) {
        return RedisUtil.getJedis().get(key);
    }
    
    public static void del(String key) {
        RedisUtil.getJedis().del(key);
    }
    
    public static void set(byte[] key,byte[] value) {
        RedisUtil.getJedis().del(key);
        RedisUtil.getJedis().set(key, value);
    }
    
    public static byte[] get(byte[] key) {
        return RedisUtil.getJedis().get(key);
    }
    
    public static void del(byte[] key) {
        RedisUtil.getJedis().del(key);
    }
    
    

//List---------------------------------------------------------------    
    public static void setList(String key,List<String> value) {
        RedisUtil.getJedis().del(key);
        if (null != value) {
            for (String string : value) {
                RedisUtil.getJedis().lpush(key, string);
            }
        }
        
    }
    
    public static List<String> getList(String key) {
        if (StringUtil.isNotBlank(key)) {
            return RedisUtil.getJedis().lrange(key, 0,-1);
        }
        return null;
    }
    
    public static void delList(String key) {
        RedisUtil.getJedis().del(key);
    }
    
//map---------------------------------------------------------------
    public static void setMap(String key,Map<String, String> map) {
        RedisUtil.getJedis().hmset(key, map);
    }
    
    public static List<String> getMap(String key) {
        return RedisUtil.getJedis().hvals(key);
    }
    
}
