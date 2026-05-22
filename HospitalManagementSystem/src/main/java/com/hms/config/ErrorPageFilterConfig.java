package com.hms.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorPageFilterConfig {

    @Bean
    FilterRegistrationBean<ErrorPageFilter> disableErrorPageFilter(ErrorPageFilter filter) 
    {
        FilterRegistrationBean<ErrorPageFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}