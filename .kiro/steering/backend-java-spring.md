---
inclusion: fileMatch
fileMatchPattern: '**/*.java'
---

# Backend Development Guidelines - Java Spring Boot

## Technology Stack
- **Java**: 25 (LTS, released September 2025)
- **Spring Boot**: 3.5.6 (latest stable)
- **Build Tool**: Maven 3.9+

## CRITICAL: Context7 MCP Requirement
**ALWAYS consult Context7 MCP before writing ANY code.**
- You don't know anything about any library or API
- Never rely on pre-trained information
- Never search in other places
- Never hallucinate or assume
- All technical knowledge MUST come from Context7 MCP

Use `resolve_library_id` then `get_library_docs` for:
- Spring Boot API usage
- Java 25 features
- Maven configuration
- HTTP client usage (RestClient)

## Code Style
- Use records for DTOs (ColorRequest, ColorResponse)
- Use constructor injection (no field injection)
- Keep methods under 20 lines
- Use meaningful variable names
- Add comments only for non-obvious logic

## What NOT to Implement
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

## Common Pitfalls to Avoid
- Don't use `@EnableWebMvc` (breaks Spring Boot auto-config)
- Don't configure `RestTemplate` (use `RestClient` instead)
- Don't add complex exception handling (keep it simple)
- Don't optimize prematurely (code clarity > performance)
- Hardcoded bulb IP will probably be wrong - ask for real IP before testing
- Shelly uses gen1 API: simple GET requests, not RPC

## File Generation Order
1. `pom.xml` - Dependencies first
2. `application.properties` - Configuration
3. Model classes - Simple records
4. `ShellyBulbService` - Core logic
5. `ColorController` - REST endpoint
6. `VibeCodingApplication` - Main class

## Design Philosophy
Intentionally prioritize simplicity: NO cloud, databases, Docker, or complex infrastructure.
