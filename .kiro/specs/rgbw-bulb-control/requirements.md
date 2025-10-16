# Requirements Document

## Introduction

This document specifies the requirements for the RGBW Bulb Control Application, a web-based system that captures webcam video, detects the dominant color in the video feed, and controls a Shelly Duo GU10 RGBW smart bulb over a local network. The application is designed for live conference demonstrations with emphasis on simplicity, reliability, and visibility.

## Glossary

- **Web Application**: The complete system consisting of Backend Server and Frontend Client
- **Backend Server**: Spring Boot application providing REST API endpoints for bulb control
- **Frontend Client**: HTML5/JavaScript browser application providing user interface
- **Webcam Feed**: Real-time video stream from user's camera device
- **Dominant Color**: RGB color value calculated from webcam frame pixels
- **Shelly Bulb**: Shelly Duo GU10 RGBW smart bulb controlled via HTTP API
- **Manual Mode**: Operating mode where user triggers color transmission via button
- **Auto Mode**: Operating mode where color transmission occurs automatically every 3 seconds
- **Color Preview**: Visual display showing current detected dominant color
- **Camera Selector**: Dropdown UI element for choosing webcam device

## Requirements

### Requirement 1

**User Story:** As a conference presenter, I want to select my webcam from a dropdown list, so that I can use the correct camera for color detection.

#### Acceptance Criteria

1. WHEN THE Frontend Client loads, THE Camera Selector SHALL populate with available camera devices
2. WHEN THE user selects a camera from THE Camera Selector, THE Webcam Feed SHALL activate and display video from the selected device
3. IF THE selected camera becomes unavailable, THEN THE Frontend Client SHALL display an error message and allow camera reselection
4. THE Frontend Client SHALL request camera permissions without requiring manual user approval prompts

### Requirement 2

**User Story:** As a conference presenter, I want to see the dominant color detected from my webcam in real-time, so that I know what color will be sent to the bulb.

#### Acceptance Criteria

1. WHILE THE Webcam Feed is active, THE Frontend Client SHALL calculate the dominant color from the current video frame
2. WHILE THE Webcam Feed is active, THE Color Preview SHALL update continuously to display the current dominant color
3. THE Frontend Client SHALL use native JavaScript getImageData() method for color detection
4. THE Frontend Client SHALL calculate dominant color by averaging RGB values across sampled pixels

### Requirement 3

**User Story:** As a conference presenter, I want to manually send the detected color to the bulb with a button press, so that I can control exactly when the bulb changes color.

#### Acceptance Criteria

1. WHEN THE user clicks the send button in Manual Mode, THE Frontend Client SHALL transmit the current dominant color to THE Backend Server
2. WHEN THE Backend Server receives a color request, THE Backend Server SHALL send the color command to THE Shelly Bulb via HTTP GET request
3. THE Backend Server SHALL complete the color change within 2 seconds of receiving the request
4. THE Frontend Client SHALL display the send button with minimum height of 50 pixels for touch-friendly interaction

### Requirement 4

**User Story:** As a conference presenter, I want an automatic mode that sends colors every 3 seconds, so that the bulb continuously reflects the webcam colors without manual intervention.

#### Acceptance Criteria

1. WHEN THE user activates Auto Mode, THE Frontend Client SHALL transmit the current dominant color to THE Backend Server every 3 seconds
2. WHILE Auto Mode is active, THE Frontend Client SHALL continue automatic transmission until the user deactivates Auto Mode
3. THE Frontend Client SHALL provide a toggle control with minimum height of 50 pixels to switch between Manual Mode and Auto Mode
4. WHEN THE user deactivates Auto Mode, THE Frontend Client SHALL stop automatic color transmission immediately

### Requirement 5

**User Story:** As a conference presenter, I want the application to handle network failures gracefully, so that the demo continues even if the bulb is temporarily offline.

#### Acceptance Criteria

1. IF THE Backend Server cannot reach THE Shelly Bulb, THEN THE Backend Server SHALL return an error response to THE Frontend Client
2. WHEN THE Frontend Client receives an error response, THE Frontend Client SHALL display an error message to the user
3. IF THE Shelly Bulb is offline, THEN THE Frontend Client SHALL continue operating and allow retry attempts
4. THE Web Application SHALL continue functioning without crashes when network errors occur

### Requirement 6

**User Story:** As a conference presenter, I want the UI to be visible from the back of the room, so that audience members can see the application during the demo.

#### Acceptance Criteria

1. THE Frontend Client SHALL render all interactive buttons with minimum height of 50 pixels
2. THE Frontend Client SHALL use high contrast colors for text and UI elements
3. THE Color Preview SHALL be large enough to be visible from a distance of 10 meters
4. THE Frontend Client SHALL use CSS Grid or Flexbox for responsive layout

### Requirement 7

**User Story:** As a developer, I want the application to start with a single command, so that setup is quick and reliable for the demo.

#### Acceptance Criteria

1. THE Web Application SHALL start completely using the single Maven command "mvn spring-boot:run"
2. THE Backend Server SHALL use embedded Tomcat server without requiring external application server
3. THE Backend Server SHALL serve THE Frontend Client static files from the classpath
4. THE Web Application SHALL require zero configuration files beyond application.properties

### Requirement 8

**User Story:** As a developer, I want the application to use simple technology without complex infrastructure, so that the codebase is easy to explain during the demo.

#### Acceptance Criteria

1. THE Backend Server SHALL use Spring Boot 3.5.6 with Java 25
2. THE Backend Server SHALL use RestClient for HTTP communication with THE Shelly Bulb
3. THE Frontend Client SHALL use vanilla HTML5 and ES2024 JavaScript without frameworks
4. THE Web Application SHALL operate without databases, Docker containers, or cloud services

### Requirement 9

**User Story:** As a developer, I want the Shelly bulb integration to use the Gen1 API, so that color commands work reliably with simple HTTP GET requests.

#### Acceptance Criteria

1. THE Backend Server SHALL communicate with THE Shelly Bulb using Shelly Gen1 HTTP API
2. THE Backend Server SHALL send color commands to THE Shelly Bulb using HTTP GET requests
3. THE Backend Server SHALL accept configurable Shelly Bulb IP address via application.properties
4. THE Backend Server SHALL construct color command URLs with RGB parameters in the format expected by Shelly Gen1 API
