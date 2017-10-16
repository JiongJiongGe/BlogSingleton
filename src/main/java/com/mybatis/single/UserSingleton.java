package com.mybatis.single;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by yunkai on 2017/10/11.
 */
public class UserSingleton {

    private static Logger logger = LoggerFactory.getLogger(UserSingleton.class);

    private LinkedHashMap<String, String> linkMap;

    public volatile static UserSingleton userSingleton;

    private UserSingleton(){}

    public static String getUserSingleton(String key){
        if(userSingleton == null){
            synchronized (UserSingleton.class){
                if(userSingleton == null){
                    userSingleton = new UserSingleton();
                    LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                    userSingleton.setLinkMap(map);
                }
            }
        }
        logger.info("userSingleton = {}", new Gson().toJson(userSingleton));
        return userSingleton.getLinkMap().get(key);
    }

    public static UserSingleton setUserSingleton(String key, String value){
        synchronized (UserSingleton.class){
            LinkedHashMap<String, String> map = userSingleton.getLinkMap();
            if(StringUtils.isEmpty(map.get(key))){
                map.put(key, value);
                userSingleton.setLinkMap(map);
            }
        }
        logger.info("userSingleton = {}", new Gson().toJson(userSingleton));
        return userSingleton;
    }

    public LinkedHashMap<String, String> getLinkMap() {
        return linkMap;
    }

    public void setLinkMap(LinkedHashMap<String, String> linkMap) {
        this.linkMap = linkMap;
    }
}
