package com.zhb.forever.redis.client;

import com.zhb.forever.framework.spring.bean.locator.SpringBeanLocator;
import com.zhb.forever.redis.Constants;

public class RedisClientFactory {
    
    public static RedisClient getRedisClientBean() {
        Object bean = SpringBeanLocator.getInstance(
                Constants.REDIS_CLIENT_CONF).getBean(
                        Constants.REDIS_CLIENT);
        return (RedisClient) bean;
    }

}
