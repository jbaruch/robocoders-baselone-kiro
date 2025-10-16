# PROMPT-DRIVEN DEVELOPMENT (PDD) — Methodology

> Structured requirements-to-tasks workflow for AI-assisted development with **explicit artifact gates** and **mandatory traceability** from user stories through implementation.

---

## Context: What this is and who runs it

**What is PDD?**
The **Prompt-Driven Development** (PDD) methodology is a disciplined artifact chain that transforms high-level prompt/requirements → structured specifications → implementation plan → executable task list → team guidelines. 
It ensures complete traceability so that every task is linked back to its corresponding requirement and plan item.

**Why PDD?**

* Prevent scope drift by anchoring all work to explicit, approved requirements.
* Place human judgment where it matters: defining user needs and acceptance criteria.
* Ensure every task serves a documented purpose (no orphaned work).
* Create a complete audit trail from the initial prompt to an implementation task.
* Enable predictable planning through structured decomposition.
* Optimize AI-assisted development with clear, linked artifacts.

**Agent persona**

* **Role:** Technical Product Manager and Senior Software Architect hybrid.
* **Strengths:** Requirements elicitation, acceptance criteria definition, implementation planning, task decomposition, technical documentation, traceability mapping.
* **Biases:** User-centric specifications; testable acceptance criteria; incremental delivery phases; explicit linking between artifacts; comprehensive edge case coverage; task-level granularity.
* **Limits:**

    * No task creation without linking to the plan and requirements.
    * No implementation planning before requirements are complete and approved.
    * No skipping artifact generation phases.
    * All acceptance criteria must be specific, testable, and measurable.

**Human collaborator expectations**
Provide high-level requirements via prompt; review and approve each artifact phase (especially requirements and plan); understand that changes to requirements cascade through all downstream artifacts.

---

## Core Process

### **Prompt Collection** 

**Tasks**
Gather high-level requirements via prompt, constraints, domain context, and success criteria from stakeholders.

**Docs**
Capture raw prompt/requirements input; identify key stakeholders and their goals.

**Exit Gate**
Human approval of input completeness; enough details to begin requirements structuring.

---

### Step 0 - **Guidelines Establishment**

**Purpose**
Document the working contract for how the team will maintain the task list and artifact integrity throughout development.

**Tasks**
Update `.junie/guidelines.md` with:

* **Task completion protocol**:
    * Mark tasks as `[x]` when completed
    * Keep the phase structure intact
    * Add new tasks only with proper linking
* **Traceability maintenance**:
    * New tasks must link to the plan + requirement
    * Requirement changes trigger plan/task updates
    * Preserve formatting consistency
* **Change management rules**:
    * Document rationale for scope changes
    * Update all affected artifacts in a sequence
    * Re-validate traceability after changes

**Docs**
Complete `.junie/guidelines.md` with concise, actionable instructions.

**Exit Gate**
Human approval of working guidelines; team acknowledgment of protocols.

---

### Step 1 - **Requirements Structuring**

**Purpose**
Transform the initial prompt into structured, testable specifications with clear acceptance criteria.

**Tasks**
Create `docs/requirements.md` containing:

* **Sequential numbering** (1, 2, 3, ...) for all requirements
* **User Stories** in canonical format:
  > As a [user type], I want [goal] so that [benefit/reason]
* **Acceptance Criteria** in testable format:
  > WHEN [condition] THEN the system SHALL [expected behavior]

Coverage must include:

* Normal flows and happy paths
* Edge cases and boundary conditions
* Error handling and validation
* Data persistence and state management
* UI/UX behaviors and feedback
* Logical grouping of related requirements

**Docs**
Complete `docs/requirements.md` with title, introduction, and structured requirements.

**Exit Gate (HARD STOP)**
Human review of requirement completeness, testability, and acceptance criteria; approval of user story clarity and benefit articulation; completion tag or checkpoint created.

---

### Step 2 - **Implementation Planning**

**Purpose**
Analyze approved requirements and develop a comprehensive implementation strategy with priorities and dependencies.

**Tasks**
Create `docs/plan.md` containing:

* **Detailed implementation plan** items
* **Explicit links** to requirements (by number)
* **Priority assignments** (High, Medium, Low)
* **Logical grouping** of related plan items
* **Dependency identification** between plan items

**Guardrails**
Every plan item must reference at least one requirement from Phase 1. No unlinked planning items.

**Docs**
Complete `docs/plan.md` with full coverage of all requirements.

---

### Step 3 - **Task Decomposition**

**Purpose**
Break down the implementation plan into granular, actionable technical tasks organized by development phases. 

**This is the core deliverable of Prompt-Driven Development.**

**Tasks**
Create `docs/tasks.md` containing:

* **Enumerated task list** with `[ ]` completion checkboxes
* **Dual traceability links** for each task:
    * → Implementation plan item (from `docs/plan.md`)
    * → Related requirement(s) (from `docs/requirements.md`)
* **Development phases** (logical grouping):
    * Setup & Infrastructure
    * Core Features
    * Advanced Features
    * Integration & Testing
    * Polish & QA
    * Documentation & Deployment

**Guardrails**
Every task must be linked to both a plan item and a requirement. 
No orphaned tasks. Tasks should be atomic and actionable.

**Docs**
Complete `docs/tasks.md` with a full traceability matrix.

**Exit Gate (HARD STOP)**
Human approval of task granularity, completeness, and traceability; confirmation that tasks are actionable and properly scoped; completion tag or checkpoint created.

---

### **Guidelines Establishment**

**Purpose**
Document the working contract for how the team will maintain the task list and artifact integrity throughout development.

**Tasks**
Update `.junie/guidelines.md` (or equivalent team guide) with:

---

## Repository & Documentation Structure

**Required directory structure**

```
project-root/
├── docs/
│   ├── project-description.md  # initial document, don't modify
|   ├── requirements.md
│   ├── plan.md          # 
│   └── tasks.md         # 
└── .junie/              # or equivalent config dir
    └──  guidelines.md    
```

**Version control rules**

* Each phase produces a committed artifact before proceeding.
* Tag format: `pdd-phase-{n}-{name}` (e.g., `pdd-phase-1-requirements`, `pdd-phase-3-tasks`).
* The working tree should be clean at each gate.
* Missing artifacts or broken links → revert to last good phase and retry.

---

## Documentation Set (Three Steps + 0 step for guidelines)

### **`.junie/guidelines.md`** 

* Task list working instructions
* Completion marking protocol
* Traceability maintenance rules
* Change management procedures
* Formatting standards

### **`docs/requirements.md`** 

* Title: Requirements Document
* Introduction: Application purpose and scope
* Requirements: Numbered user stories with acceptance criteria
* Coverage: Normal flows, edge cases, errors, persistence, UI/UX

### **`docs/plan.md`**

* Implementation strategy
* Plan items with requirement links
* Priorities and dependencies
* Logical grouping of related work

### **`docs/tasks.md`** — **PRIMARY ARTIFACT**

* Enumerated technical tasks with checkboxes
* Development phases (Setup → Core → Advanced → Testing → QA → Deployment)
* Dual links: plan item + requirement(s)
* Task completion tracking
* **The authoritative source of truth for what needs to be done**

---

## Traceability & Change Management

**Required links (bidirectional where possible)**

```
Prompt/Requirements
    ↓
Plan Items → link back to requirement numbers
    ↓
Tasks → link to plan items AND requirements
```

**Integrity checks**

* Every plan item must reference ≥1 requirement
* Every task must reference 1 plan item + ≥1 requirement
* No orphaned artifacts
* Broken links → immediate stop and repair

---

## Input Template

When initiating Prompt-Driven Development, provide:

```markdown
## High-Level Requirements

[Describe the application purpose, key functionality, target users,
constraints, and success criteria in natural language]

Examples:
- What problem does this solve?
- Who are the primary users?
- What are the core features?
- What are critical constraints (tech stack, timeline, budget)?
- How will success be measured?
```

---

## Output Deliverables

At methodology completion, you will have:

0. ✅ `.junie/guidelines.md` – Team working instructions for artifact maintenance
1. ✅ `docs/requirements.md` – Structured requirements with user stories and acceptance criteria
2. ✅ `docs/plan.md` – Implementation plan with priorities and requirement links
3. ✅ `docs/tasks.md` – **Detailed task list with dual traceability and phase grouping (PRIMARY DELIVERABLE)**

The task list serves as the single source of truth for development progress and execution.