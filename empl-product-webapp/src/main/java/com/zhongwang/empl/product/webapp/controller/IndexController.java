package com.zhongwang.empl.product.webapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhongwang.empl.product.cache.redis.SimpleRedis;
import com.zhongwang.empl.product.cache.redis.util.RedisUtils;
import com.zhongwang.empl.product.webapp.domain.User;
import com.zhongwang.empl.product.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 9:43d
 * @version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Resource(name = "simpleRedis")
    private SimpleRedis simpleRedis;

    @GetMapping({"/", ""})
    public String welcome(Model model) {
        return "redirect:/index";
    }

    @GetMapping({"/index"})
    public String index(Model model) {
        String userPkid = "";
        List<User> userList = userService.selectByUserName("ahwangyuchao", "853a5f00bd8b460da167973222cefef1");
        User user = userList.get(0);
        userPkid = user.getPkid();
        //这里相当于redis对String类型的set操作
        String firstKey = "firstKey";
        RedisUtils.set("firstKey", "hello redis", 60);
        //这里相当于redis对String类型的get操作
        String test = RedisUtils.getValue(firstKey, String.class);
        System.out.println("from redis cache get string key value is === >>> " + test);
        RedisUtils.set(userPkid, user, 60);
        User beforeCacheUser = RedisUtils.getValue(userPkid, User.class);
        System.out.println("add from redis cache get object key value is === >>> " + beforeCacheUser);
        // RedisUtils.remove(userPkid);
        User afterCacheUser = RedisUtils.getValue(userPkid, User.class);
        System.out.println("del from redis cache get object key value is === >>> " + afterCacheUser);

        /**
         Fastjson的SerializerFeature序列化属性
         QuoteFieldNames———-输出key时是否使用双引号,默认为true 
         WriteMapNullValue——–是否输出值为null的字段,默认为false 
         WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
         WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
         WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
         WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
         **/

        String jsonString = JSONObject.toJSONString(afterCacheUser, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse);
        System.out.println("对象json格式 \n " + jsonString);
        return "index";
    }
}
