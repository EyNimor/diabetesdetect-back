package com.mediscreen.diabetesdetect.main.feign;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediscreen.diabetesdetect.main.config.FeignConfiguration;
import com.mediscreen.diabetesdetect.main.constant.ReportStatus;

@FeignClient(name = "ReportClient", url = "${feign.client.url.reportUrl}", configuration = FeignConfiguration.class)
public interface ReportClient {
    
    @GetMapping("/generate")
    public ReportStatus generateReport(@RequestParam(value = "id") UUID patientId, @RequestParam(value = "age") int patientAge, @RequestParam(value = "sex") String patientSex);

}
