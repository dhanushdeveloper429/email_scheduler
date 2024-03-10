package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiCheckService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EmailService emailService;


    @Value("${api.endpoints}")
    private String apiEndpoints;

    public void checkApiStatus() {
  System.out.println("Service started");
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo("dhanushdeveloper.java@gmail.com");
        emailMessage.setSubject("API Status Error");
        StringBuilder text = new StringBuilder();
        for (String endpoint : apiEndpoints.split(",")) {
        	System.out.println("Hello"+endpoint);
            String[] keyValue = endpoint.split("=");
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(keyValue[1], String.class);
                if (response.getStatusCodeValue() != 200) {
                    text.append("The API ").append(keyValue[0]).append(" returned a status of ").append(response.getStatusCodeValue()).append(". Please check the API.\n");
                }
            } catch (HttpClientErrorException e) {
                text.append("The API ").append(keyValue[0]).append(" returned a status of ").append(e.getRawStatusCode()).append(". Please check the API.\n");
            }
        }
        if (text.length() > 0) {
            emailMessage.setText(text.toString());
            emailService.sendEmail(emailMessage);
      
        } else {
        
        }
    }
}