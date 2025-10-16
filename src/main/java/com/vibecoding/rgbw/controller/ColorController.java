package com.vibecoding.rgbw.controller;

import com.vibecoding.rgbw.model.ColorRequest;
import com.vibecoding.rgbw.model.ColorResponse;
import com.vibecoding.rgbw.service.ShellyBulbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ColorController {

    private static final Logger logger = LoggerFactory.getLogger(ColorController.class);
    
    private final ShellyBulbService shellyBulbService;

    public ColorController(ShellyBulbService shellyBulbService) {
        this.shellyBulbService = shellyBulbService;
    }

    @PostMapping("/color")
    public ResponseEntity<ColorResponse> setColor(@RequestBody ColorRequest request) {
        try {
            logger.info("Received color request: {}", request);
            shellyBulbService.setColor(request.red(), request.green(), request.blue());
            return ResponseEntity.ok(new ColorResponse(true, "Color set successfully"));
        } catch (Exception e) {
            logger.error("Failed to set color", e);
            return ResponseEntity.status(500)
                    .body(new ColorResponse(false, "Bulb offline"));
        }
    }
}
