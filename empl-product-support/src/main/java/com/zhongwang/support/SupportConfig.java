package com.zhongwang.support;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: support配置入口，调用模块只需要引入此配置就行
 * @author: slliver
 * @date: 2019/9/5 9:48
 * @version: 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.zhongwang.support")
public class SupportConfig {

}
