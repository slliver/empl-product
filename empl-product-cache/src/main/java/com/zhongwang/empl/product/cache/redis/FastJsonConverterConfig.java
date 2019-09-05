package com.zhongwang.empl.product.cache.redis;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 配置FastJson返回json属性设置
 * @author: slliver
 * @date: 2019/9/5 13:30
 * @version: 1.0
 */
//@Configuration
public class FastJsonConverterConfig {

    @Bean
    public HttpMessageConverters customConverters() {
        // 定义一个转换消息的对象

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 这里就是核心代码了，WriteMapNullValue把空的值的key也返回
        /**
         Fastjson的SerializerFeature序列化属性
         QuoteFieldNames———-输出key时是否使用双引号,默认为true 
         WriteMapNullValue——–是否输出值为null的字段,默认为false 
         WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
         WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
         WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
         WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
         **/
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);

        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        // 处理中文乱码问题
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
        stringConverter.setSupportedMediaTypes(fastMediaTypes);

        // 将转换器添加到converters中
        return new HttpMessageConverters(stringConverter, fastConverter);
    }
}
