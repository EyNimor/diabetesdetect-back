package com.mediscreen.diabetesdetect.reportgenerator.unit;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mediscreen.diabetesdetect.reportgenerator.feign.HistoryClient;
import com.mediscreen.diabetesdetect.reportgenerator.service.ReportGeneratorService;

//TODO: Create Units Tests for Report Generation
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReportGenerationTests {
    
    private static HistoryClient historyClient;

    private static ReportGeneratorService service;

    @PostConstruct
    private void setUp() {

    }

    @Test
    public void RiskLevelNoneTest() {

    }

    @Test
    public void RiskLevelBorderlineTest() {

    }

    @Test
    public void RiskLevelInDangerWithMenUnderThirtyTest() {

    }

    @Test
    public void RiskLevelInDangerWithWomenUnderThirtyTest() {
        
    }

    @Test
    public void RiskLevelInDangerWithPatientOverThirtyTest() {
        
    }

    @Test
    public void RiskLevelEarlyOnsetWithMenUnderThirtyTest() {

    }

    @Test
    public void RiskLevelEarlyOnsetWithWomenUnderThirtyTest() {

    }

    @Test
    public void RiskLevelEarlyOnsetWithPatientOverThirtyTest() {

    }

}
