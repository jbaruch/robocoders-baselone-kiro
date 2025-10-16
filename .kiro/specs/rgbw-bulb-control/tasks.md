# Implementation Plan

- [x] 1. Set up Spring Boot project structure and dependencies
  - Create Maven pom.xml with Spring Boot 3.5.6, Java 25, and Spring Web dependencies
  - Configure application.properties with server port and Shelly bulb IP placeholder
  - Create package structure: controller, service, model
  - _Requirements: 7.1, 7.2, 8.1, 9.3_

- [x] 2. Implement backend data models
  - Create ColorRequest record with red, green, blue integer fields
  - Create ColorResponse record with success boolean and message string fields
  - _Requirements: 8.1_

- [x] 3. Implement Shelly bulb service layer
  - Create ShellyBulbService class with RestClient for HTTP communication
  - Implement setColor method that constructs Shelly Gen1 API URL with RGB parameters
  - Add error handling for network failures using try-catch for RestClientException
  - Inject bulb IP from application.properties using @Value annotation
  - _Requirements: 3.2, 5.1, 8.2, 9.1, 9.2, 9.4_

- [x] 4. Implement REST API controller
  - Create ColorController with POST /api/color endpoint
  - Implement request handling that accepts ColorRequest and returns ColorResponse
  - Add constructor injection for ShellyBulbService
  - Implement error response handling that returns ColorResponse with success=false on failures
  - _Requirements: 3.1, 3.2, 5.2, 8.1_

- [x] 5. Create Spring Boot main application class
  - Create VibeCodingApplication class with @SpringBootApplication annotation
  - Implement main method that calls SpringApplication.run
  - _Requirements: 7.1, 7.2_

- [x] 6. Implement frontend HTML structure
  - Create index.html with camera selector dropdown element
  - Add video element for webcam preview display
  - Add color preview div element for showing detected color
  - Create send button with minimum 50px height for manual mode
  - Create auto mode toggle switch with minimum 50px height
  - Add error message display area
  - Add hidden canvas element for color detection processing
  - _Requirements: 1.1, 1.2, 2.2, 3.4, 4.3, 5.2, 6.1, 6.3_

- [x] 7. Implement camera management JavaScript module
  - Write initializeCameraSelector function that enumerates devices using navigator.mediaDevices.enumerateDevices
  - Implement startCamera function that activates video stream using getUserMedia with selected deviceId
  - Add camera error handling that displays error messages and allows reselection
  - Implement automatic camera permission request on page load
  - _Requirements: 1.1, 1.2, 1.3, 1.4, 5.3_

- [x] 8. Implement color detection JavaScript module
  - Write detectDominantColor function that draws video frame to canvas and uses getImageData
  - Implement pixel sampling algorithm that averages RGB values across sampled pixels
  - Create updateColorPreview function that updates color preview div background
  - Add continuous color detection loop that runs while video is active
  - _Requirements: 2.1, 2.2, 2.3, 2.4_

- [x] 9. Implement mode control JavaScript module
  - Write sendColorToBackend function that POSTs color data to /api/color endpoint
  - Implement manual mode send button click handler
  - Create toggleAutoMode function that starts/stops 3-second interval timer
  - Add auto mode interval logic that calls sendColorToBackend every 3 seconds
  - Implement error display function that shows backend error messages
  - Add response time validation ensuring color changes complete within 2 seconds
  - _Requirements: 3.1, 3.2, 3.3, 4.1, 4.2, 4.3, 4.4, 5.2, 5.3_

- [x] 10. Implement frontend CSS styling
  - Create CSS Grid layout for main application structure
  - Style video preview element to be large and visible
  - Style color preview with large square display
  - Apply high contrast colors for visibility from distance
  - Ensure all buttons have minimum 50px height with touch-friendly styling
  - Add responsive layout using Flexbox for button controls
  - _Requirements: 6.1, 6.2, 6.3, 6.4_

- [x] 11. Create backend unit tests
  - [x] 11.1 Write ShellyBulbService tests with mocked RestClient
    - Mock RestClient to verify correct Shelly Gen1 URL construction
    - Test RGB value formatting in URL parameters
    - Verify error handling when RestClientException is thrown
    - _Requirements: 9.1, 9.2, 9.4_
  - [x] 11.2 Write ColorController tests
    - Test ColorRequest to ColorResponse mapping
    - Verify ShellyBulbService method invocation
    - Test error response format when service throws exception
    - _Requirements: 3.1, 5.1_

- [ ]* 12. Create Playwright integration tests
  - [ ]* 12.1 Write camera selector test
    - Verify dropdown populates with camera devices
    - Test camera selection triggers video stream
    - _Requirements: 1.1, 1.2_
  - [ ]* 12.2 Write color detection test
    - Verify video stream displays in preview
    - Test color preview updates continuously
    - _Requirements: 2.1, 2.2_
  - [ ]* 12.3 Write manual mode test
    - Test send button triggers color transmission
    - Verify color change completes within 2 seconds
    - _Requirements: 3.1, 3.2, 3.3_
  - [ ]* 12.4 Write auto mode test
    - Verify auto mode sends color every 3 seconds
    - Test toggle switch activates and deactivates auto mode
    - _Requirements: 4.1, 4.2, 4.3, 4.4_
  - [ ]* 12.5 Write error handling test
    - Test error message display when backend unreachable
    - Verify graceful degradation when bulb offline
    - _Requirements: 5.1, 5.2, 5.3_
  - [ ]* 12.6 Write cross-browser compatibility test
    - Test application in Chrome, Firefox, and Safari
    - _Requirements: 6.4_
