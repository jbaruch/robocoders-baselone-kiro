---
inclusion: manual
---

# Testing Strategy

## Integration Testing via Playwright
Use headless browsers for end-to-end testing.

### Checklist
- [ ] Camera selection dropdown populates
- [ ] Video stream displays from selected camera
- [ ] Color preview updates continuously
- [ ] Manual mode: send button works
- [ ] Auto mode: color sends every 3 seconds
- [ ] Bulb actually changes color
- [ ] Error messages display on network failure
- [ ] App works on Chrome, Firefox, Safari

## Unit Tests (Optional for Demo)
- `ShellyBulbService`: Mock RestClient calls
- `ColorController`: Test request/response mapping

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
