package com.mybatis.service;

import com.google.gson.Gson;
import com.mybatis.controller.SingletonController;
import com.mybatis.domain.user.CalmWangUserModel;
import com.mybatis.service.user.CalmWangUserServiceI;
import com.mybatis.single.UserSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by yunkai on 2017/10/16.
 */
@Service
public class SingleApplyService {

    private Logger logger = LoggerFactory.getLogger(SingletonController.class);

    @Autowired
    private CalmWangUserServiceI calmWangUserService;

    public String retun(String key){
        String value = UserSingleton.getUserSingleton(key);
        if(StringUtils.isEmpty(value)){
            CalmWangUserModel user = calmWangUserService.getByPhone(key);
            UserSingleton.setUserSingleton(key, user.getUserName());
            value = UserSingleton.getUserSingleton(key);
        }
        return value;
    }
}
