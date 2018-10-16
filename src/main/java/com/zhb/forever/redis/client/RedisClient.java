package com.zhb.forever.redis.client;

import java.util.List;
import java.util.Map;

public interface RedisClient {
    
  //单个对象---------------------------------------------------------------
    /**
     * *添加，key，value
     * @param String,String
     */
     void set(String key,String value) ;
   
     /**
      * *获取
      * @param String
      * @return String
      */
     String get(String key) ;
   
     /**
      * *删除
      * @param String
      */
     void del(String key) ;
     
   
     /**
      * *添加，key，value
      * @param byte[] ,byte[]
      */
     void set(byte[] key,byte[] value) ;
   
     /**
      * *获取
      * @param byte[] 
      * @return  byte[] 
      */
     byte[] get(byte[] key) ;
   
     /**
      * *删除
      * @param byte[]
      */
     void del(byte[] key) ;
   

//List---------------------------------------------------------------    
     
     /**
      * *添加List
      * @param key，List
      * @return
      */
     void setList(String key,List<String> value) ;
   
     /**
      * *获取List
      * @param key
      * @return
      */
     List<String> getList(String key) ;
   
     void delList(String key) ;
     
     
//map---------------------------------------------------------------
     void setMap(String key,Map<String, String> map) ;
   
     List<String> getMap(String key) ;

}
