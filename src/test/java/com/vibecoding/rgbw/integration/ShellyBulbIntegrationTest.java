package com.vibecoding.rgbw.integration;

import com.vibecoding.rgbw.service.ShellyBulbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Integration test that actually tries to communicate with the Shelly bulb.
 * This test will FAIL until you configure the correct bulb IP in application.properties.
 * 
 * Expected behavior:
 * - FAILS with current placeholder IP (192.168.1.100)
 * - PASSES once you update shelly.bulb.ip with your real bulb IP
 */
@SpringBootTest
class ShellyBulbIntegrationTest {

    @Autowired
    private ShellyBulbService shellyBulbService;

    @Test
    void testActualBulbCommunication() {
        // This will fail if the bulb IP is wrong or bulb is unreachable
        // It will pass once you configure the real IP and the bulb is online
        assertDoesNotThrow(() -> {
            shellyBulbService.setColor(255, 0, 0); // Red
        }, "Failed to communicate with Shelly bulb. Update shelly.bulb.ip in application.properties");
    }

    @Test
    void testBulbAcceptsMultipleColors() {
        // Test that we can send multiple color commands
        assertDoesNotThrow(() -> {
            shellyBulbService.setColor(0, 255, 0);   // Green
            Thread.sleep(500); // Small delay between commands
            shellyBulbService.setColor(0, 0, 255);   // Blue
            Thread.sleep(500);
            shellyBulbService.setColor(255, 255, 0); // Yellow
        }, "Failed to send multiple color commands to bulb");
    }
}
