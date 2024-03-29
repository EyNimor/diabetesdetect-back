package com.mediscreen.diabetesdetect.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.mediscreen.diabetesdetect.main.annotation.ExcludeFromJacocoGeneratedReport;

@SpringBootApplication
@EnableFeignClients
@ExcludeFromJacocoGeneratedReport
public class MainApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
