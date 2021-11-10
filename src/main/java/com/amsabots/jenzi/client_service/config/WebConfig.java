package com.amsabots.jenzi.client_service.config;

import com.amsabots.jenzi.client_service.utils.StringToEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author andrew mititi on Date 11/10/21
 * @Project lameck-client-service
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }
}
