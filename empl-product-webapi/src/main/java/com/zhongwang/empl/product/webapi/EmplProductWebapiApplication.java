package com.zhongwang.empl.product.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmplProductWebapiApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EmplProductWebapiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EmplProductWebapiApplication.class, args);
    }

}
