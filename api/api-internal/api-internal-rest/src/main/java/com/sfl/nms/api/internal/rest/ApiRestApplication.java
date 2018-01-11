package com.sfl.nms.api.internal.rest;

import com.sfl.nms.api.internal.rest.web.filter.ApplicationExceptionHandlingFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;

/**
 * Company: SFL LLC
 * Created on 03/12/2017
 *
 * @author Davit Harutyunyan
 */
@SpringBootApplication
@ComponentScan("com.sfl.nms.api.internal.rest")
@ImportResource({"classpath:applicationContext-api-internal-rest.xml", "classpath:applicationContext-queue-producer.xml"})
public class ApiRestApplication {

    @Value("${server.servlet.context-path}")
    private String urlMapping;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiRestApplication.class).run(args);
    }

    @Bean
    public ServletRegistrationBean jerseyServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), urlMapping + "/*");
        // our rest resources will be available in the path /rest/*
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, NotificationJerseyConfig.class.getName());

        return registration;
    }

    @Bean
    public FilterRegistrationBean exceptionHandlingFilterRegistration() {

        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ApplicationExceptionHandlingFilter());
        registration.addUrlPatterns("/*");
        registration.setName("applicationExceptionHandlingFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean encodingFilterRegistration() {

        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter());
        registration.addUrlPatterns("/*");
        registration.setName("encoding.filter");
        final HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("encoding", "UTF-8");
        registration.setInitParameters(initParameters);
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean entityManagerFilterRegistration() {

        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new OpenEntityManagerInViewFilter());
        registration.addUrlPatterns("/*");
        registration.setName("openEntityManagerInViewFilter");
        final HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("entityManagerFactory", "entityManagerFactory");
        registration.setInitParameters(initParameters);
        registration.setOrder(3);
        registration.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
        return registration;
    }

}