package org.smartinrub.welcomeservice;

import org.smartinrub.welcomeservice.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public FilterRegistrationBean<AuthFilter> AuthFilter() {
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthFilter());
        bean.addUrlPatterns("/welcome");
        bean.setOrder(1);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
