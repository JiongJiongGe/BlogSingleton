package com.mybatis.service.impl.redis;

import com.mybatis.service.redis.RedisServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yunkai on 2017/10/11.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisServiceI {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void setKeyValue(String key, String value){
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        //valueOps.set(key, value, 60, TimeUnit.SECONDS);   //设置过期时间
        valueOps.set(key, value);
    }

    @Override
    public String getValue(String key){
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(key);
    }

    @Override
    public void setKeyValue(String key, LinkedHashMap<String, String> map){
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public LinkedHashMap<String, String> getMapValue(String key){
        return (LinkedHashMap<String, String>) redisTemplate.opsForHash().entries(key);
    }


}