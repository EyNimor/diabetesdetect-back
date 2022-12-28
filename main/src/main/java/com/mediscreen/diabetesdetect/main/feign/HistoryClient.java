package com.mediscreen.diabetesdetect.main.feign;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediscreen.diabetesdetect.main.config.FeignConfiguration;
import com.mediscreen.diabetesdetect.main.model.History;

@FeignClient(name = "HistoryClient", url = "http://localhost:8083/history/", configuration = FeignConfiguration.class)
public interface HistoryClient {
    
    @GetMapping("/getHistoryByPatientId")
    List<History> getHistoryByPatientId(@RequestParam(value = "uuid") UUID patientId);

    @GetMapping("/getOneNote")
    History getOneNote(@RequestParam(value = "uuid") UUID noteId);

    @PostMapping("/saveHistory")
    void saveHistory(@RequestBody History newHistory);

    @PutMapping("/updateHistory")
    void updateHistory(@RequestBody History historyToUpdate);

}
