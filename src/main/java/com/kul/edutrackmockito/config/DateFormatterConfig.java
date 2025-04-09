package com.kul.edutrackmockito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;


/**
 * author: Kul Paudel
 * createdDate: 2025-04-09
 **/
@Configuration
public class DateFormatterConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
