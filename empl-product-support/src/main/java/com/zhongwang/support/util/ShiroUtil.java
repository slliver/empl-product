package com.zhongwang.support.util;

import com.zhongwang.support.base.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 16:19
 * @version: 1.0
 */
public class ShiroUtil {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getUser() {
        Object object = getSubjct().getPrincipal();
        return (SysUser) object;
    }

    public static String getUserId() {
        return getUser().getPkid();
    }

    public static String getCurrentUserPkid() {
        return getUser().getPkid();
    }

    public static String getCompPkid() {
        return getUser().getCompPkid();
    }

    public static void logout() {
        getSubjct().logout();
    }

}
