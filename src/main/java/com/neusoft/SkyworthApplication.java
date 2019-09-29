package com.neusoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动类：部署到外部servlet容器时需要继承SpringBootServletInitializer并重写configure()
 *
 * @author：yu8home
 * @date：2018年7月3日 上午11:52:10
 */
@SpringBootApplication
public class SkyworthApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SkyworthApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SkyworthApplication.class, args);
    }

}