package com.zhongwang.empl.product.webapp.dao;

import com.zhongwang.empl.product.webapp.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

    /**
     * 根据登录号和公司码获取用户对象，包含所属角色
     * @param userName userName
     * @param compPkid compPkid
     * @return
     */
    List<User> selectByUserName(@Param("userName") String userName, @Param("compPkid") String compPkid);

}
