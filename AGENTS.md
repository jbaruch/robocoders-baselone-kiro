# AI Agent Guidelines - Vibecoding Demo

## Project Overview
Build a web app that uses webcam feed to detect dominant color and control a Shelly Duo GU10 RGBW smart bulb over the local network.

**Demo Context**: Live conference demo using agentic AI IDEs.

## 🚨 CRITICAL: Context7 MCP Requirement

**ALWAYS consult Context7 MCP before writing ANY code.**

You don't know anything about any library or API:
- ❌ Never rely on pre-trained information
- ❌ Never search in other places
- ❌ Never hallucinate or assume
- ✅ All technical knowledge MUST come from Context7 MCP

### Required MCP Workflow
1. Use `resolve_library_id` to find the correct library
2. Use `get_library_docs` to get current documentation
3. Only then write code based on retrieved docs

### Libraries to Query
- Spring Boot 3.5.6
- Java 25
- MediaStream API
- Canvas API (getImageData)
- Modern JavaScript (ES2024)
- CSS Grid/Flexbox

---

## Technology Stack

### Backend
- **Java**: 25 (LTS, released September 2025)
- **Spring Boot**: 3.5.6 (latest stable)
- **Build Tool**: Maven 3.9+

### Frontend
- **Core**: Vanilla HTML5 + Modern JavaScript (ES2024)
- **Color Detection**: Native JS getImageData() on camera preview
- **Styling**: Modern CSS with CSS Grid/Flexbox

---

## Design Philosophy

**Intentionally prioritize simplicity**: NO cloud, databases, Docker, or complex infrastructure.

---

## What NOT to Implement

### Backend
❌ WebSocket connections
❌ Circuit breakers (Resilience4j)
❌ Connection pooling configuration
❌ Actuator endpoints
❌ Prometheus metrics
❌ Redis caching
❌ Database (PostgreSQL, H2, etc.)
❌ Docker containerization
❌ MQTT protocol
❌ Authentication/Authorization
❌ Rate limiting
❌ Logging frameworks beyond SLF4J default

### Frontend
❌ WebSocket client
❌ K-means clustering for color
❌ OffscreenCanvas
❌ Web Workers
❌ HSL/HSV conversions
❌ Kelvin temperature calculations
❌ Complex color science
❌ MediaStream constraints configuration
❌ Frontend frameworks (React, Vue, Angular)
❌ Build tools (Webpack, Vite)
❌ CSS preprocessors (Sass, Less)

---

## Code Style

### Backend (Java)
- Use records for DTOs (ColorRequest, ColorResponse)
- Use constructor injection (no field injection)
- Keep methods under 20 lines
- Use meaningful variable names
- Add comments only for non-obvious logic

### Frontend (JavaScript)
- Use modern ES2024 syntax
- Keep functions focused and small
- Use descriptive variable names
- Avoid premature optimization

---

## Common Pitfalls to Avoid

### Backend
- Don't use `@EnableWebMvc` (breaks Spring Boot auto-config)
- Don't configure `RestTemplate` (use `RestClient` instead)
- Don't add complex exception handling (keep it simple)
- Hardcoded bulb IP will probably be wrong - ask for real IP before testing
- Shelly uses gen1 API: simple GET requests, not RPC

### Frontend
- Don't overthink camera selection (basic dropdown is fine)
- Don't optimize prematurely (code clarity > performance)
- Keep color detection simple (just sample center pixels)

---

## File Generation Order

### Backend First
1. `pom.xml` - Dependencies first
2. `application.properties` - Configuration
3. Model classes - Simple records
4. `ShellyBulbService` - Core logic
5. `ColorController` - REST endpoint
6. `VibeCodingApplication` - Main class

### Frontend Second
7. `index.html` - UI structure
8. `app.js` - Client logic
9. `styles.css` - Styling

---

## Usability Requirements
- Zero configuration for end user
- Large, touch-friendly buttons (50px+ height)
- High contrast for visibility from distance

## Reliability Requirements
- Graceful degradation if bulb offline
- Auto-reconnect camera if stream drops
- No crashes on invalid camera selection

---

## Acceptance Criteria

The demo is successful if:
- ✅ App runs with single `mvn spring-boot:run` command
- ✅ Webcam activates without manual permission prompts
- ✅ Color preview visibly updates in real-time
- ✅ Manual send changes bulb color within 2 seconds
- ✅ Auto mode sends a color every 3 seconds reliably
- ✅ UI is large enough to see from the back of the conference room
- ✅ No crashes or exceptions during demo
- ✅ Code is simple enough to explain in 5 minutes
