package com.mybatis.service.redis;

import java.util.LinkedHashMap;

/**
 * Created by yunkai on 2017/10/11.
 */
public interface RedisServiceI {

    /**
     * redis 设置<key, value>
     * @param key
     * @param value
     */
    public void setKeyValue(String key, String value);

    public void setKeyValue(String key, LinkedHashMap<String, String> map);

    /**
     * redis 通过key获取value
     * @param key
     * @return
     */
    public String getValue(String key);

    public LinkedHashMap<String, String> getMapValue(String key);
}
