# Vibecoding Demo: RGBW Control App PRD

## Project Overview

**Goal**: Build a web app that uses webcam feed to detect dominant color and control a Shelly Duo GU10 RGBW smart bulb over the local network.

**Demo Context**: live conference demo using agentic AI IDEs and tools Jetbrains Junie and Amazon Kiro.

**Design Philosophy**: Intentionally prioritize simplicity: NO cloud, databases, Docker, or complex infrastructure.

---

## Technology Stack

### Backend
- **Java**: 25 (LTS, released September 2025)
- **Spring Boot**: 3.5.6 (latest stable)
- **Build Tool**: Maven 3.9+

### Frontend
- **Core**: Vanilla HTML5 + Modern JavaScript (ES2024)
- **Color Detection**: use native JS getImageData() on camera preview
- **Styling**: Modern CSS with CSS Grid/Flexbox


## Non-Functional Requirements

### Usability
- Zero configuration for end user
- Large, touch-friendly buttons (50px+ height)
- High contrast for visibility from distance

### Reliability
- Graceful degradation if bulb offline
- Auto-reconnect camera if stream drops
- No crashes on invalid camera selection

---

## Out of Scope (DO NOT IMPLEMENT)

The following is explicitly **excluded** to maintain simplicity:

### Backend
- ❌ WebSocket connections
- ❌ Circuit breakers (Resilience4j)
- ❌ Connection pooling configuration
- ❌ Actuator endpoints
- ❌ Prometheus metrics
- ❌ Redis caching
- ❌ Database (PostgreSQL, H2, etc.)
- ❌ Docker containerization
- ❌ MQTT protocol
- ❌ Authentication/Authorization
- ❌ Rate limiting
- ❌ Logging frameworks beyond SLF4J default

### Frontend
- ❌ WebSocket client
- ❌ K-means clustering for color
- ❌ OffscreenCanvas
- ❌ Web Workers
- ❌ HSL/HSV conversions
- ❌ Kelvin temperature calculations
- ❌ Complex color science
- ❌ MediaStream constraints configuration
- ❌ Frontend frameworks (React, Vue, Angular)
- ❌ Build tools (Webpack, Vite)
- ❌ CSS preprocessors (Sass, Less)

---

## Testing Strategy

### Integration Testing via headless browsers (Playwright) Checklist
- [ ] Camera selection dropdown populates
- [ ] Video stream displays from selected camera
- [ ] Color preview updates continuously
- [ ] Manual mode: send button works
- [ ] Auto mode: color sends every 3 seconds
- [ ] Bulb actually changes color
- [ ] Error messages display on network failure
- [ ] App works on Chrome, Firefox, Safari

### Unit Tests (Optional for Demo)
- `ShellyBulbService`: Mock RestClient calls
- `ColorController`: Test request/response mapping

## Acceptance Criteria

**The demo is successful if**:
- ✅ App runs with single `mvn spring-boot:run` command
- ✅ Webcam activates without manual permission prompts
- ✅ Color preview visibly updates in real-time
- ✅ Manual send changes bulb color within 2 seconds
- ✅ Auto mode sends a color every 3 seconds reliably
- ✅ UI is large enough to see from the back of the conference room
- ✅ No crashes or exceptions during demo
- ✅ Code is simple enough to explain in 5 minutes

---

## Additional Notes for AI Agents

### File Generation Order
1. `pom.xml` - Dependencies first
2. `application.properties` - Configuration
3. Model classes - Simple records
4. `ShellyBulbService` - Core logic
5. `ColorController` - REST endpoint
6. `VibeCodingApplication` - Main class
7. `index.html` - UI structure
8. `app.js` - Client logic
9. `styles.css` - Styling

### Common Pitfalls to Avoid
- Don't use `@EnableWebMvc` (breaks Spring Boot auto-config)
- Don't configure `RestTemplate` (use `RestClient` instead)
- Don't add complex exception handling (keep it simple)
- Don't overthink camera selection (basic dropdown is fine)
- Don't optimize prematurely (code clarity > performance)
- Hardcoded bulb IP will probably be wrong, so it will fail until the user provides real IP. ask for IP soon, before testing.
- Shelly has numerous API generations; use gen1, which uses simple GET requests, not RPC.

### Code Style Preferences
- Use records for DTOs (ColorRequest, ColorResponse)
- Use constructor injection (no field injection)
- Keep methods under 20 lines
- Use meaningful variable names
- Add comments only for non-obvious logic
