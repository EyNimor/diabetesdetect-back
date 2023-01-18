package com.mediscreen.diabetesdetect.reportgenerator.feign;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mediscreen.diabetesdetect.reportgenerator.config.FeignConfiguration;
import com.mediscreen.diabetesdetect.reportgenerator.model.Note;

@FeignClient(name = "HistoryClient", url = "${feign.client.url.historyUrl}", configuration = FeignConfiguration.class)
public interface HistoryClient {
    
    @GetMapping("/getHistoryByPatientId")
    List<Note> getHistoryByPatientId(@RequestParam(value = "uuid") UUID patientId);

    @GetMapping("/getOneNote")
    Note getOneNote(@RequestParam(value = "uuid") UUID noteId);

    @PostMapping("/saveHistory")
    void saveHistory(@RequestBody Note newHistory);

    @PutMapping("/updateHistory")
    void updateHistory(@RequestBody Note historyToUpdate);

}
