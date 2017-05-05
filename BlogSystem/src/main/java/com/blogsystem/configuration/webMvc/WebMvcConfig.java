package com.blogsystem.configuration.webMvc;

import com.blogsystem.interceptor.TitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Spaskich on 5.5.2017 г..
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final TitleInterceptor titleInterceptor;


    @Autowired
    public WebMvcConfig(TitleInterceptor titleInterceptor) {
        this.titleInterceptor = titleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(titleInterceptor);
    }
}
