package com.zhongwang.support.constants;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 16:31
 * @version: 1.0
 */
public class Constants {

    // 系统默认公司SYSTEM
    public static final String SYS_RESERVED_COMPANY_CODE = "SYSTEM";
    public static final String SYS_TREE_PATH_SPLITER = "\\";
    // 系统默认树结构的根节点
    public static final String SYS_TREE_ROOT_PARENTID = "00";
    public static final String SYS_DEFAULT_PASSWORD = "123456";
    public static final String SYS_DEFAULT_PHONE = "88800000000";

    // 账户状态启用
    public static final Boolean ACCOUNT_STATUS_ENABLE = Boolean.TRUE;
    // 账户状态启用
    public static final Boolean ACCOUNT_STATUS_DISABLE = Boolean.FALSE;
    // 执行结果成功
    public static final String OPER_RESULT_SUCCESS = "success";
    // 执行结果失败
    public static final String OPER_RESULT_FAIL = "fail";

    // redis Key默认过期时间(单位：秒)
    public static final short REDIS_KEY_DEFAULT_EXPIRE_TIME = 3000;

    // 当前操作是否显示：是
    public static final Short FLAG_SHOW_YES = 1;
    // 当前操作是否显示：否
    public static final Short FLAG_SHOW_NO = 0;

    public static class SYS_COMMON_STATUS {
        public static final Short VALID = 1;
        public static final Short INVALID = 0;
        public static final Short DELETE = 1;
        public static final Short NOT_DELETE = 0;
        public static final String STR_TRUE = "true";
        public static final String STR_FALSE = "true";
    }
}
