---
inclusion: fileMatch
fileMatchPattern: '**/*.{html,js,css}'
---

# Frontend Development Guidelines - Vanilla JavaScript

## Technology Stack
- **Core**: Vanilla HTML5 + Modern JavaScript (ES2024)
- **Color Detection**: Native JS getImageData() on camera preview
- **Styling**: Modern CSS with CSS Grid/Flexbox

## CRITICAL: Context7 MCP Requirement
**ALWAYS consult Context7 MCP before writing ANY code.**
- You don't know anything about any library or API
- Never rely on pre-trained information
- Never search in other places
- Never hallucinate or assume
- All technical knowledge MUST come from Context7 MCP

Use `resolve_library_id` then `get_library_docs` for:
- MediaStream API
- Canvas API (getImageData)
- Modern JavaScript (ES2024) features
- CSS Grid/Flexbox

## Usability Requirements
- Zero configuration for end user
- Large, touch-friendly buttons (50px+ height)
- High contrast for visibility from distance

## Reliability Requirements
- Graceful degradation if bulb offline
- Auto-reconnect camera if stream drops
- No crashes on invalid camera selection

## What NOT to Implement
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

## Common Pitfalls to Avoid
- Don't overthink camera selection (basic dropdown is fine)
- Don't optimize prematurely (code clarity > performance)
- Keep color detection simple (just sample center pixels)

## File Generation Order
1. `index.html` - UI structure
2. `app.js` - Client logic
3. `styles.css` - Styling

## Design Philosophy
Intentionally prioritize simplicity: NO frameworks, build tools, or complex infrastructure.
