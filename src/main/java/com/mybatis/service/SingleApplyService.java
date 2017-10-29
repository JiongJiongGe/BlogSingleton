package com.mybatis.service;

import com.google.gson.Gson;
import com.mybatis.controller.SingletonController;
import com.mybatis.domain.user.CalmWangUserModel;
import com.mybatis.serialize.SerializeProxy;
import com.mybatis.service.redis.RedisServiceI;
import com.mybatis.service.user.CalmWangUserServiceI;
import com.mybatis.single.UserSingleton;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;

/**
 * Created by yunkai on 2017/10/16.
 */
@Service
public class SingleApplyService {

    private Logger logger = LoggerFactory.getLogger(SingletonController.class);

    @Autowired
    private CalmWangUserServiceI calmWangUserService;

    @Autowired
    private RedisServiceI redisService;

    public String retun(String key){
        String value = UserSingleton.getUserSingletonValue(key);
        if(StringUtils.isEmpty(value)){
            try {
                logger.info("in = in");
                CalmWangUserModel user = calmWangUserService.getByPhone(key);
                UserSingleton.setUserSingleton(key, user.getUserName());
                LinkedHashMap<String, String> map = UserSingleton.getUserSingleton();
                redisService.setKeyValue("all", map);
                value = UserSingleton.getUserSingletonValue(key);
            }catch (Exception e){
                logger.error("error = {}", e);
            }
        }
        return value;
    }
}
