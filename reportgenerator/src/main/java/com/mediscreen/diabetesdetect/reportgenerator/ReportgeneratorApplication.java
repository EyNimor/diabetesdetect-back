package com.mediscreen.diabetesdetect.reportgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.mediscreen.diabetesdetect.reportgenerator.annotation.ExcludeFromJacocoGeneratedReport;

@SpringBootApplication
@EnableFeignClients
@ExcludeFromJacocoGeneratedReport
public class ReportgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportgeneratorApplication.class, args);
	}

}