package com.mybatis.controller;


import com.mybatis.service.SingleApplyService;
import com.mybatis.service.redis.RedisServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yunkai on 2017/10/12.
 */
@RestController
@RequestMapping("single")
public class SingletonController {

    private Logger logger = LoggerFactory.getLogger(SingletonController.class);

    @Autowired
    private SingleApplyService singleApplyService;

    @GetMapping("singleton")
    public String singleton(String key){
       String value = singleApplyService.retun(key);
       logger.info("userName = {}", value);
       return value;
    }

}
