package com.zhongwang.empl.product.webapp.domain;

import com.zhongwang.support.base.domain.SysUser;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Description: 用户domain实体
 * @author: slliver
 * @date: 2019/3/8 14:53
 * @version: 1.0
 */
@Data
@Table(name = "sys_user")
public class User extends SysUser {

    @Transient
    private String statusName;
    //当前保存部门
    @Transient
    private String organPkid;
    //修改前部门
    @Transient
    private String oldOrganPkid;

    @Transient
    private String organName;
    @Transient
    private String postPkid;
    @Transient
    private String rankPkid;
    /**
     * 原始密码
     */
    @Transient
    private String oldPassword;
    /**
     * 密码
     */
    @Transient
    private String password;
    /**
     * 确认密码
     */
    @Transient
    private String confirmPassword;

    @Transient
    private String userRoles;
    //角色
    @Transient
    private List<String> roleIds;
}
