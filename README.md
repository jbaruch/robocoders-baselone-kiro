# Robocoders BaselOne 2025

A demonstration repository showcasing different AI-assisted development methodologies for building software with AI coding agents. 
This project was created for a conference demo at BaselOne 2025, demonstrating how different agentic AI IDEs and tools can work with structured methodologies.

> **🎯 This is a Template Repository**  
> Use this as a starter for your own AI-assisted coding projects! 
> Click the **"Use this template"** button at the top of the GitHub page to create your own repository.
> Then, select a methodology (PDD or IIC) and let your AI coding tool build your project using the provided prompts and guidelines.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Sample Project: RGBW Control App](#sample-project-rgbw-control-app)
- [Methodologies](#methodologies)
  - [Prompt-Driven Development (PDD)](#prompt-driven-development-pdd)
  - [Intent Integrity Chain (IIC)](#intent-integrity-chain-iic)
- [Repository Structure](#repository-structure)
- [Getting Started](#getting-started)
- [License](#license)

## 🎯 Project Overview

This repository demonstrates **structured approaches to AI-assisted software development**. 
Rather than adhoc prompting, it showcases disciplined methodologies that provide:

- **Traceability**: Every code change traces back to requirements
- **Quality gates**: Human review at critical decision points
- **Artifact management**: Explicit documentation and version control
- **Predictable outcomes**: Structured workflows reduce AI hallucination risks

The repository contains:
1. Two complete methodology specifications - Prompt-Driven Development (PDD) and Intent Integrity Chain (IIC)
2. Sample project requirements (RGBW smart bulb control app)
3. Starter prompts and guidelines for AI agents

## 💡 Sample Project: RGBW Control App

The sample project is a **Vibecoding Demo** - a web application that:

- Uses webcam feed to detect dominant colors in real-time
- Controls a Shelly Duo GU10 RGBW smart bulb over the local network
- Provides both manual and automatic color control modes
- Runs entirely locally (no cloud, databases, or Docker)

**Technology Stack:**
- **Backend**: Java 25 + Spring Boot 3.5.6 + Maven
- **Frontend**: Vanilla HTML5 + Modern JavaScript (ES2024) + Color Thief 2.4.0
- **Design Philosophy**: Intentional simplicity for live demo purposes

See [`docs/project-description.md`](docs/project-description.md) for complete specifications.

## 📚 Methodologies

### Prompt-Driven Development (PDD)

**Philosophy**: Structured requirements-to-tasks workflow with explicit artifact gates and mandatory traceability.

**Process Flow**:
```
Prompt → Requirements → Implementation Plan → Tasks → Guidelines
```

**Key Features**:
- Sequential numbering of all requirements
- Dual traceability links (plan → requirements, tasks → plan + requirements)
- Development phases: Setup → Core → Advanced → Testing → QA → Deployment
- Primary artifact: `docs/tasks.md` (the authoritative task list)

**When to Use**:
- Projects with clear requirements upfront
- Teams that value comprehensive planning
- Scenarios requiring full audit trails
- Incremental delivery with defined milestones

**Documentation**: See [`docs/prompt-driven-development.md`](docs/prompt-driven-development.md)

### Intent Integrity Chain (IIC)

**Philosophy**: Test-first software delivery with gated phases and mandatory stop after test generation for human review.

**Process Flow**:
```
Init → Analysis → Specification → Test Construction (STOP) → Implementation → Hardening
```

**Key Features**:
- **Hard stop after test generation** - humans approve tests before code
- Locked tests cannot be modified during implementation
- Phase-based tagging protocol
- Behavioral specifications (Gherkin or equivalent)
- NFR validation in hardening phase

**When to Use**:
- Test-driven development workflows
- Quality-critical applications
- Teams that want to validate test coverage before implementation
- Scenarios requiring explicit phase gates

**Documentation**: See [`docs/intent-integrity-chain.md`](docs/intent-integrity-chain.md)

## 📁 Repository Structure

```
robocoders-baselone/
├── docs/
│   ├── prompt-driven-development.md    # PDD methodology specification
│   ├── intent-integrity-chain.md       # IIC methodology specification
│   └── project-description.md          # Sample project (RGBW Control App) PRD
├── prompts/
│   ├── 01_generate-rules.txt           # Initial setup prompt
│   ├── 02_starter_prompt.txt           # Project kickoff prompt
│   └── kiro/
│       └── hook.txt                    # Kiro-specific prompt hooks
├── LICENSE                             # MIT License
└── README.md                           # This file
```

### Key Files

- **`docs/prompt-driven-development.md`**: Complete PDD methodology with artifact structure, traceability rules, and process gates
- **`docs/intent-integrity-chain.md`**: Complete IIC methodology with phase definitions and test-first workflow
- **`docs/project-description.md`**: Sample project requirements demonstrating how to structure specifications for AI agents
- **`prompts/01_generate_rules.txt`**: Instructions for generating methodology-specific steering documents
- **`prompts/02_starter_prompt.txt`**: Template for starting project development with AI agents

## 🚀 Getting Started

### For Humans

**Quick Start: Use This Template**

1. **Create your repository**: Click the **"Use this template"** button at the top of this GitHub page
2. **Choose a methodology** based on your project needs:
   - Want comprehensive planning upfront? → Use PDD
   - Want test-first with quality gates? → Use IIC
3. **Read the methodology document** thoroughly:
   - PDD: [`docs/prompt-driven-development.md`](docs/prompt-driven-development.md)
   - IIC: [`docs/intent-integrity-chain.md`](docs/intent-integrity-chain.md)
4. **Use the starter prompts** in the `prompts/` directory with your AI coding tool to build your project
5. **Let your AI tool work**: Follow the methodology's workflow, using the provided prompts to guide the AI agent

**Learning Mode**

If you want to explore the methodologies before using the template:

1. **Review the sample project** ([`docs/project-description.md`](docs/project-description.md)) to see how requirements should be structured
2. **Study the methodology documents** to understand the workflow and artifact requirements
3. **Examine the starter prompts** to see how to structure your initial requests to AI agents

### For AI Agents

If you're an AI agent being directed to this repository:

1. **Read the methodology document** specified by the human (PDD or IIC)
2. **Follow the process exactly** - methodologies have explicit gates and artifact requirements
3. **Use the starter prompts** as templates for understanding the workflow
4. **Consult Context7 MCP** before writing any code (per methodology requirements)
5. **Maintain traceability** - every artifact must link to its predecessor
6. **Stop at gates** - wait for human approval before proceeding to the next phase

## 🤝 Contributing

This repository demonstrates methodologies for AI-assisted development. 
Contributions should maintain the philosophy of structured, traceable, gated workflows.

## 📄 License

MIT License.
See [LICENSE](LICENSE) for details.

Copyright (c) 2025 Baruch Sadogursky, Viktor Gamov

## 🔗 Additional Resources

- [**BaselOne 2025**](https://baselone.org/en/baselone-home/#schedule)
- [**Shownotes**](https://shownotes.tuxcare.com/robocoders-judgment-day-ai-ides-face-off-baselone-2025/)

---

**Note**: This repository is a demonstration of methodologies, not a complete implementation. 
Use it as a template and starting point for your own AI-assisted development projects.
