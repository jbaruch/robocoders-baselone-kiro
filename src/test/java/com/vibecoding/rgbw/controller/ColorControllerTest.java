package com.vibecoding.rgbw.controller;

import com.vibecoding.rgbw.model.ColorRequest;
import com.vibecoding.rgbw.model.ColorResponse;
import com.vibecoding.rgbw.service.ShellyBulbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColorControllerTest {

    @Mock
    private ShellyBulbService shellyBulbService;

    @InjectMocks
    private ColorController colorController;

    @Test
    void testSetColor_Success() {
        ColorRequest request = new ColorRequest(255, 128, 64);
        doNothing().when(shellyBulbService).setColor(255, 128, 64);

        ResponseEntity<ColorResponse> response = colorController.setColor(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().success());
        assertEquals("Color set successfully", response.getBody().message());
        verify(shellyBulbService).setColor(255, 128, 64);
    }

    @Test
    void testSetColor_ServiceThrowsException() {
        ColorRequest request = new ColorRequest(100, 100, 100);
        doThrow(new RuntimeException("Bulb offline")).when(shellyBulbService).setColor(100, 100, 100);

        ResponseEntity<ColorResponse> response = colorController.setColor(request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertFalse(response.getBody().success());
        assertEquals("Bulb offline", response.getBody().message());
    }

    @Test
    void testSetColor_InvokesServiceWithCorrectParameters() {
        ColorRequest request = new ColorRequest(0, 255, 0);
        doNothing().when(shellyBulbService).setColor(0, 255, 0);

        colorController.setColor(request);

        verify(shellyBulbService, times(1)).setColor(0, 255, 0);
    }
}
