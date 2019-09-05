package com.zhongwang.empl.product.webapp;

import com.zhongwang.empl.product.cache.CacheConfig;
import com.zhongwang.support.SupportConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.zhongwang.**.dao")
@SpringBootApplication
@Import({SupportConfig.class, CacheConfig.class})
public class EmplProductWebappApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EmplProductWebappApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmplProductWebappApplication.class, args);
    }

}
