package ru.rival.airvalue.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/**")
                .addResourceLocations("WEB-INF/static/css/");

        registry.addResourceHandler("scripts/**")
                .addResourceLocations("WEB-INF/static/js/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("WEB-INF/static/img/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
