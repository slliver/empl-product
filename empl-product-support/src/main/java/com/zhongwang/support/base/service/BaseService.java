package com.zhongwang.support.base.service;

import com.zhongwang.support.base.entity.BaseDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 用一句话具体描述类的功能
 * @author: slliver
 * @date: 2019/9/3 16:05
 * @version: 1.0
 */
public class BaseService<T extends BaseDomain>{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


}
