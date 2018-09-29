package com.zhb.forever.redis.client;

import java.util.List;
import java.util.Set;

public interface RedisClient {
    
    /**
     * *添加，key，value
     * @param key,value
     */
    void set(String key,Object value);
    
    /**
     * *获取
     * @param key
     * @return
     */
    Object get(String key);
    
    /**
     * *添加List
     * @param key，List
     * @return
     */
    void addList(String key,List<?> value);
    
    /**
     * *获取List
     * @param key
     * @return
     */
    List<?> getList(String key);
    
    /**
     * *添加Set
     * @param key，Set
     * @return
     */
    void addSet(String key,Set<?> value);
    
    /**
     * *获取Set
     * @param key
     * @return
     */
    Set<?> getSet(String key);
    
}
