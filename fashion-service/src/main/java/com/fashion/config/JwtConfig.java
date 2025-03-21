package com.fashion.config;

import com.fashion.filter.FaishonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public FilterRegistrationBean getBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new FaishonFilter());
        bean.addUrlPatterns("*");
        return bean;
    }
}
