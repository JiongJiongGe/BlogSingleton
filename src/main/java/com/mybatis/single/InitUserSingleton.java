package com.mybatis.single;

import com.mybatis.domain.user.CalmWangUserModel;
import com.mybatis.serialize.SerializeProxy;
import com.mybatis.service.redis.RedisServiceI;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Created by yunkai on 2017/10/24.
 */
@Configuration
public class InitUserSingleton {

    private static Logger logger = LoggerFactory.getLogger(InitUserSingleton.class);

    @Autowired
    private RedisServiceI redisService;

    @Bean
    public UserSingleton init(){
        LinkedHashMap<String, String> map = redisService.getMapValue("all");
        return UserSingleton.setUserSingletonMap(map);
    }
}
