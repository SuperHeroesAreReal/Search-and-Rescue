# Search and Rescue (S+R)

![OhBuoy - stack trace ocean](assets/ohbuoy-stack-trace-ocean.png)

Search and Rescue (S+R) is the full NodAIity orientation toolkit.

It brings together independently wieldable instruments for continuity, reconnaissance, bearing, position, and posture. Use one instrument or combine the smallest useful set for the terrain.

```text
OhBuoy     -> Is the route alive?
RECCE      -> What is actually out there?
Compass    -> Which way should I face?
Plotter    -> Where am I?
AIluminode -> How should I proceed?
```

This repository contains the runnable OhBuoy implementation, full-stack documentation, release-hardening evidence, and S+R benchmark plans.

Related standalone tool:

- [AIluminode](https://github.com/SuperHeroesAreReal/AIluminode)

---

## Runnable Continuity Instrument

OhBuoy is a lightweight continuity scanner.

It is not observability tooling, telemetry, distributed tracing, dashboards, AI orchestration, network surveillance, or infrastructure crawling.

Audits tell you what exists. OhBuoy tells you what happens along declared terrain.

Core question:

```text
Is the route alive?
```

Core loop:

```text
scan
packet
render
release
```

V0 doctrine:

```text
ping before ponder
no answer is an answer
minimal memory is sacred
the scanner must float above terrain, not become the terrain
```

---

## Use

Build the local CLI package:

```powershell
.\gradlew.bat :cli:installDist
```

Run the scanner:

```powershell
.\cli\build\install\ohbuoy\bin\ohbuoy.bat startup
```

Equivalent scan form:

```powershell
.\cli\build\install\ohbuoy\bin\ohbuoy.bat scan startup
```

Optional AIluminode posture scan mode in this repo:

```powershell
.\cli\build\install\ohbuoy\bin\ohbuoy.bat eeg "Refactor Paula EPUB source handling without touching memory save logic"
```

---

## Related Toolkit

OhBuoy is an independently deployable continuity instrument and a pane in **Search and Rescue (S+R)**, the full NodAIity orientation toolkit.

S+R combines independently wieldable orientation panes without turning them into a fixed pipeline or shared runtime. Use OhBuoy alone when the question is route continuity; use the smallest useful Glass Stack when the terrain needs more illumination.

Explore the current S+R release and benchmark evidence:

- [S+R Release Hardening](docs/S+R%20Release%20Hardening%201.md)
- [S+R Test 1: Prove Orientation](docs/S+R%20Test%201.md)
- [S+R Test 2: Measure Work Saved](docs/S+R%20Test%202.md)
- [S+R Test 3: Doctrine Propagation](docs/S+R%20Test%203.md)

Toolkit questions:

```text
OhBuoy     -> Is the route alive?
RECCE      -> What is actually out there?
Compass    -> Which way should I face?
Plotter    -> Where am I?
AIluminode -> How should I proceed?
```

No tool here is a required dependency of another tool. Each instrument should still make sense by itself.

Glass Stack doctrine:

```text
No pane owns the terrain.
No pane owns another pane.
Use the smallest set of panes needed before action.
```

Route polarity is a corridor, not a blindfold.

The scanner is operationally blinkered but architecturally peripheral-aware: it narrows action to declared terrain while still allowing side-door observations to be reported as evidence.

Blocked terrain prevents implementation drift. It does not erase architectural awareness.

Stable corridors need lighter orientation overhead. Uncertain, dark, or stale terrain should trigger a fresh Compass and AIluminode posture check before action.

```text
if terrain feels stale
→ ping orientation again
→ refresh route polarity
→ act only inside the updated corridor
```

---

## Orientation Before Retrieval

OhBuoy is part of the broader NodAIity orientation research effort.

Current NodAIity doctrine explores the idea that many AI failures may occur before retrieval rather than during reasoning.

Orientation does not replace retrieval or reasoning.

Orientation helps determine:

- where the system is
- which direction it should face
- which terrain is active
- which routes should remain closed

Core doctrine:

```text
Don't reason harder.
Orient first.

Don't run faster.
Follow the compass.
```

Compass does not choose direction. Compass reveals directional pressure already present in the terrain.

---

## Expected V0 Terrain

```text
TRACE startup_4812

AUTH ✓
TENANT ✓
GOVERNANCE ⚠
NAVIGATION ⛔

HOWLER:
Governance degradation propagated downstream.
```

Available modes:

```text
startup
login
governance
navigation
```

AIluminode's internal EEG mode emits a compact contextual posture trace:

```text
EEG TRACE eeg_1234

ACTIVE_TERRAIN:
- paula_memory_pipeline
- source_terrain
- codebase

STANCE:
surgical_refactor

COMPASS_GUIDANCE:
- route=paula_memory_pipeline
- howler=RECENT_SQLITE_MEMORY bypasses STANCE_GATE and TERRAIN_GATE.
- likely_fix=Move recent memory retrieval behind topology filtering.

ROUTE_POLARITY:
- OPEN: current_prompt (declared task is the active entry point)
- OPEN: bounded_source_index (source terrain is active; use bounded source access)
- PROTECT: saved_memory (saved_memory is named under a protection/avoidance phrase)
- AUDIT: compass:prompt_assembly (Compass target for observed propagation drift)
- DEFER: vector_memory (vector_memory is not needed for this task)
- BLOCK: autonomous_crawling (AIluminode stays declared-terrain only)

OPEN_ROUTES:
- current_prompt
- paula_memory_files
- bounded_source_index
- declared_code_surface

PROTECT:
- saved_memory

AUDIT:
- compass:prompt_assembly

DEFER:
- archive_logs
- vector_memory
- full_source_ingestion
- dashboard

BLOCK:
- autonomous_crawling
- telemetry_empire

DRIFT_RISK:
- source_terrain_mistaken_for_memory

NEXT_SAFE_ACTION:
Protect saved memory; inspect adjacent route logic without modifying it.
```

---

## V2 Compass

Compass compares declared propagation routes.

It does not inspect projects, crawl repositories, chase runtime links, or decide terrain vocabulary. The route must be declared first.

Run the Paula memory pipeline report:

```powershell
.\cli\build\install\ohbuoy\bin\ohbuoy.bat compass paula_memory_pipeline
```

Expected shape:

```text
COMPASS REPORT paula_memory_pipeline

EXPECTED:
USER_INPUT
→ STANCE_GATE
→ TERRAIN_GATE
→ BLOCKERS/SIGNPOSTS
→ FILTERED_RECENT_MEMORY
→ BOUNDED_RETRIEVAL
→ TOPOGRAPHY_PACKET
→ MODEL_PROMPT
→ MODEL_RESPONSE
→ SQLITE_MEMORY_WRITE

OBSERVED:
USER_INPUT
→ TOPOGRAPHY_PACKET
→ RECENT_SQLITE_MEMORY
→ MODEL_PROMPT
→ MODEL_RESPONSE
→ SQLITE_MEMORY_WRITE

HOWLER:
RECENT_SQLITE_MEMORY bypasses STANCE_GATE and TERRAIN_GATE.
```

---

## Boundaries

OhBuoy observes declared terrain only.

It must never become an autonomous topology crawler. It does not chase unknown links, discover infrastructure, retain operational data, ingest telemetry, or build dashboards.

The scanner emits bounded continuity packets, renders operational orientation, and releases the data.

AIluminode follows the same boundary. Its internal EEG mode observes contextual posture before action; it does not read files, retain prompts, retrieve memory, or modify terrain.

Side-door observations may inform a report, but they do not grant permission to widen the implementation route.

Dynamic re-orientation is preferred over stretching an old packet across changed terrain.

Unknown terrain is a first-class state:

```text
unknownTerrain
-> route not registered
-> position uncertain
-> bearing unavailable
-> stop propagation
-> report state
```

---

## RECCE

RECCE is the field discipline around OhBuoy.

```text
observe
orient
report
disappear
```

Scouts do not start building towers because the view is nice.

RECCE marks continuity fracture zones without fixing, rewriting, or occupying terrain.

Run RECCE doctrine tests:

```powershell
.\gradlew.bat :core:test
```

---

## Current Shape

```text
ohbuoy = observes terrain
Compass = compares expected and observed propagation
RECCE = proves field discipline
Plotter = position instrument
AIluminode = public posture-before-action orientation instrument
EEG = internal AIluminode scan mode for route polarity
larger cognition layers may signpost terrain
```

Markers, signposts, and durable topology annotations belong in a larger cognition layer, not in the scanner.

Branch A is the current preferred orientation stack for validation:

```text
Prompt
→ Stance
→ Compass
→ Plotter
→ AIluminode
→ Retrieval
→ Reasoning
→ Response
```

Branch A preserves the distinction:

```text
Compass = bearing = Which way should I face?
Plotter = position = Where am I?
```

The terrain directs Compass like a magnet.

Compass reveals directional pressure already present in the terrain.

See [BranchA.md](docs/BranchA.md).

---

## Evidence Status

Current evidence ladder:

```text
Paula
→ controlled packet proof-of-gate
→ CPO changes behavior before retrieval

Orientation Testbed Round 1
→ behavior-change benchmark
→ unknown and historical terrain pressure

Orientation Testbed Round 2
→ future paired measurement
→ work saved
```

Current caveat:

```text
Current results demonstrate behavior change before retrieval and early work-reduction signals.
They do not yet support broad drift-reduction claims.
```

The first win is better orientation.

The next measured win is work avoided.

---

## Related

Part of **Search and Rescue (S+R)**, the full NodAIity orientation toolkit.

OhBuoy can be used independently or alongside other orientation instruments. The toolkit shows how the instruments relate; it does not create a shared runtime, mandatory stack, or ownership chain.

---

## Keywords

search and rescue, S+R, AI orientation toolkit, AI navigation, developer tools, context engineering, context governance, continuity tracing, operational topology, system propagation, runtime continuity, auth drift, topology mapping, trace analysis, operational GIS, fracture detection, startup sequencing, navigation continuity, systems topography, orientation-first systems, cognitive orientation, orientation before retrieval, pre-retrieval orientation, pre-reasoning orientation, posture-before-action, cognitive posture, route polarity, bounded action, contextual routing, context drift, contextual orientation, retrieval governance, retrieval pressure, memory weighting, drift reduction, route discipline, terrain-aware systems, unknownTerrain, OhBuoy, RECCE, Compass, Plotter, AIluminode

---

![NodAIity Logo](assets/NodAIity-logo.png)

```text
Good luck sailor.
```
