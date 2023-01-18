package com.mediscreen.diabetesdetect.main.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mediscreen.diabetesdetect.main.annotation.ExcludeFromJacocoGeneratedReport;

import feign.Retryer;

@Configuration
@ExcludeFromJacocoGeneratedReport
public class FeignConfiguration {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 50);
    }
    
}
