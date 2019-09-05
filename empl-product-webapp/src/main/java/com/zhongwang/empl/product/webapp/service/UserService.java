package com.zhongwang.empl.product.webapp.service;

import com.zhongwang.empl.product.webapp.dao.UserMapper;
import com.zhongwang.empl.product.webapp.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 15:19
 * @version: 1.0
 */
@Service
public class UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public List<User> selectByUserName(String userName, String compPkid){
        return userMapper.selectByUserName(userName, compPkid);
    }

}
