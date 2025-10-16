package com.vibecoding.rgbw.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShellyBulbServiceTest {

    private ShellyBulbService shellyBulbService;
    private RestClient restClient;
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;
    private RestClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        shellyBulbService = new ShellyBulbService("192.168.1.100");
        
        restClient = mock(RestClient.class);
        requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        responseSpec = mock(RestClient.ResponseSpec.class);
        
        ReflectionTestUtils.setField(shellyBulbService, "restClient", restClient);
    }

    @Test
    void testSetColor_ConstructsCorrectUrl() {
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toBodilessEntity()).thenReturn(null);

        shellyBulbService.setColor(255, 128, 64);

        verify(requestHeadersUriSpec).uri("http://192.168.1.100/light/0?turn=on&red=255&green=128&blue=64");
    }

    @Test
    void testSetColor_HandlesRestClientException() {
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenThrow(new RestClientException("Connection failed"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            shellyBulbService.setColor(100, 100, 100);
        });

        assertEquals("Bulb offline", exception.getMessage());
    }

    @Test
    void testSetColor_FormatsRgbValuesCorrectly() {
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toBodilessEntity()).thenReturn(null);

        shellyBulbService.setColor(0, 255, 0);

        verify(requestHeadersUriSpec).uri("http://192.168.1.100/light/0?turn=on&red=0&green=255&blue=0");
    }
}
