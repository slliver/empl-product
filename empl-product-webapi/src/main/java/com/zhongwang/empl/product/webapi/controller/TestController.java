package com.zhongwang.empl.product.webapi.controller;

import com.zhongwang.support.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/2 17:01
 * @version: 1.0
 */
@RequestMapping("/api/test")
@RestController
public class TestController {

    @GetMapping("/index")
    public String index() {
        System.out.println("api current time is == >> " + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return "empl product web api test...";
    }

}
