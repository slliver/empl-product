package com.zhongwang.empl.product.cache;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: cache模块配置入口，调用模块只需要引入此配置就行
 * @author: slliver
 * @date: 2019/9/5 9:48
 * @version: 1.0
 */
@ComponentScan(basePackages = "com.zhongwang.empl.product.cache")
@Configuration
public class CacheConfig {

}
