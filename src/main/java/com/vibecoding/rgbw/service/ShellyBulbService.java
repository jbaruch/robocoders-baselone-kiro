package com.vibecoding.rgbw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class ShellyBulbService {

    private static final Logger logger = LoggerFactory.getLogger(ShellyBulbService.class);
    
    private final RestClient restClient;
    private final String bulbBaseUrl;

    public ShellyBulbService(@Value("${shelly.bulb.ip}") String bulbIp) {
        this.bulbBaseUrl = "http://" + bulbIp;
        this.restClient = RestClient.create();
    }

    public void setColor(int red, int green, int blue) {
        String url = String.format("%s/light/0?turn=on&red=%d&green=%d&blue=%d",
                bulbBaseUrl, red, green, blue);
        
        try {
            logger.info("Sending color command to Shelly bulb: R={}, G={}, B={}", red, green, blue);
            restClient.get()
                    .uri(url)
                    .retrieve()
                    .toBodilessEntity();
            logger.info("Color command sent successfully");
        } catch (RestClientException e) {
            logger.error("Failed to communicate with Shelly bulb", e);
            throw new RuntimeException("Bulb offline", e);
        }
    }
}
