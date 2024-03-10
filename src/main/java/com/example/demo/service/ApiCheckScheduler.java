package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class ApiCheckScheduler {

    @Autowired
    private ApiCheckService apiCheckService;

    @Scheduled(fixedRate = 300)
    public void scheduleCheckApiStatus() {
    	System.out.println("Helooo.........................");
        apiCheckService.checkApiStatus();
    }
}