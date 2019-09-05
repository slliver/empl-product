package com.zhongwang.support.base.domain;

import com.zhongwang.support.base.entity.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sys_user")
public class SysUser extends BaseDomain {
    /**
     * 即当前用户的登录名
     */
    @Column(name = "user_name")
    private String userName;

    private String name;

    /**
     * 1-部门；2-车间；3-区域
     */
    private String password;

    private String salt;

    /**
     * 0-未知；1-男-；2-女
     */
    private Boolean sex;

    private String phone;

    private String email;

    private String address;

    /**
     * 和系统附件表关联(获取对应缩略图)
     */
    private String avatar;

    /**
     * 10-在职；20-离职
     */
    private Short status;

    /**
     * 1-启用；0-禁用
     */
    @Column(name = "account_status")
    private Boolean accountStatus;

    private String remark;

    /**
     * 获取即当前用户的登录名
     *
     * @return user_name - 即当前用户的登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置即当前用户的登录名
     *
     * @param userName 即当前用户的登录名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取1-部门；2-车间；3-区域
     *
     * @return password - 1-部门；2-车间；3-区域
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置1-部门；2-车间；3-区域
     *
     * @param password 1-部门；2-车间；3-区域
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取0-未知；1-男-；2-女
     *
     * @return sex - 0-未知；1-男-；2-女
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置0-未知；1-男-；2-女
     *
     * @param sex 0-未知；1-男-；2-女
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取和系统附件表关联(获取对应缩略图)
     *
     * @return avatar - 和系统附件表关联(获取对应缩略图)
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置和系统附件表关联(获取对应缩略图)
     *
     * @param avatar 和系统附件表关联(获取对应缩略图)
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取10-在职；20-离职
     *
     * @return status - 10-在职；20-离职
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置10-在职；20-离职
     *
     * @param status 10-在职；20-离职
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取1-启用；0-禁用
     *
     * @return account_status - 1-启用；0-禁用
     */
    public Boolean getAccountStatus() {
        return accountStatus;
    }

    /**
     * 设置1-启用；0-禁用
     *
     * @param accountStatus 1-启用；0-禁用
     */
    public void setAccountStatus(Boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}