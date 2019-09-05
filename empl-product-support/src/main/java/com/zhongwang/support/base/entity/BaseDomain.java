package com.zhongwang.support.base.entity;

import tk.mybatis.mapper.annotation.Version;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhongwang on 2019/3/1.
 */
public class BaseDomain implements Serializable {

    @Id
    @Column(name = "pkid")
    @GeneratedValue(generator = "JDBC")
    protected String pkid;

    @Column(name = "flag_sort")
    protected Short flagSort;

    @Column(name = "flag_delete")
    protected Short flagDelete;

    /**
     * 乐观锁
     * 支持的方法
     * delete
     * deleteByPrimaryKey
     * updateByPrimaryKey
     * updateByPrimaryKeySelective
     * updateByExample
     * updateByExampleSelective
     */
    @Version
    @Column(name = "flag_version")
    protected Long flagVersion;

    @Column(name = "make_user")
    protected String makeUser;

    @Column(name = "make_time")
    protected Date makeTime;

    @Column(name = "modify_user")
    protected String modifyUser;

    @Column(name = "modify_time")
    protected Date modifyTime;

    @Column(name = "comp_pkid")
    protected String compPkid;

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public Short getFlagSort() {
        return flagSort;
    }

    public void setFlagSort(Short flagSort) {
        this.flagSort = flagSort;
    }

    public Short getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Short flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Long getFlagVersion() {
        return flagVersion;
    }

    public void setFlagVersion(Long flagVersion) {
        this.flagVersion = flagVersion;
    }

    public String getMakeUser() {
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    public Date getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Date makeTime) {
        this.makeTime = makeTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCompPkid() {
        return compPkid;
    }

    public void setCompPkid(String compPkid) {
        this.compPkid = compPkid;
    }

    public static class Columns {

        public static final String PROPERTY_PKID = "pkid";
        public static final String PROPERTY_FLAG_SORT = "flagSort";
        public static final String PROPERTY_FLAG_DELETE = "flagDelete";
        public static final String PROPERTY_FLAG_VERSION = "flagVersion";
        public static final String PROPERTY_MAKE_USER = "makeUser";
        public static final String PROPERTY_MAKE_TIME = "makeTime";
        public static final String PROPERTY_MODIFY_USER = "modifyUser";
        public static final String PROPERTY_MODIFY_TIME = "modifyTime";
        public static final String PROPERTY_COMP_PKID = "compPkid";

        public static final String PKID = "pkid";
        public static final String FLAG_SORT = "flag_sort";
        public static final String FLAG_DELETE = "flag_delete";
        public static final String FLAG_VERSION = "flag_version";
        public static final String MAKE_USER = "make_user";
        public static final String MAKE_TIME = "make_time";
        public static final String MODIFY_USER = "modify_user";
        public static final String MODIFY_TIME = "modify_time";
        public static final String COMP_PKID = "comp_pkid";
    }
}
