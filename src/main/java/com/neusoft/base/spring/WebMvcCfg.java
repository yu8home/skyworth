package com.neusoft.base.spring;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.neusoft.base.interceptor.ReqInterceptor;

/**
 * WebMvc注册
 *
 * @author：yu8home
 * @date：2018年6月2日 下午4:33:14
 */
@Configuration
public class WebMvcCfg implements WebMvcConfigurer {
    @Autowired
    private ReqInterceptor reqInterceptor;

    @Bean
    public LocaleResolver localeResolver() {
        // 相比 SessionLocaleResolver 退出后进入的首页面仍保持原有语言
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.CHINA);
        clr.setCookieMaxAge(18000);
        return clr;
    }

    // 只拦截页面跳转
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqInterceptor).addPathPatterns("/**/*-page", "/**/*-pageModel", "/loging/*", "/login", "/logout");
    }

    // 避免IE出现下载JSON文件及乱码问题
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        MappingJackson2HttpMessageConverter jcv = new MappingJackson2HttpMessageConverter();
        jcv.setSupportedMediaTypes(mediaTypes);
        converters.add(jcv);
    }

}