# OhBuoy V0 Memory

Date: 2026-05-22

OhBuoy V0 became a runnable continuity scanner.

## Core Shape

OhBuoy preserves operational orientation under complexity.

Core loop:

```text
receive
interpret
release
```

V0 remains:

```text
scan
packet
render
release
```

The scanner observes declared terrain. It does not crawl, discover, retain, orchestrate, or occupy terrain.

## Built

- Semantic core: `NodeId`, `NodeState`, `NodePacket`
- Startup continuity scanner
- Trace renderer
- Scanner modes: `startup`, `login`, `governance`, `navigation`
- CLI entrypoint: `ohbuoy startup` and `ohbuoy scan startup`
- RECCE doctrine tests
- V0 CLI package distribution
- AIluminode internal EEG posture scan mode: `ohbuoy eeg <prompt>`

Canonical sample:

```text
TRACE startup_4812

AUTH ✓
TENANT ✓
GOVERNANCE ⚠
NAVIGATION ⛔

HOWLER:
Governance degradation propagated downstream.
```

## RECCE

RECCE is field discipline around OhBuoy.

```text
observe
orient
report
disappear
```

Scouts do not start building towers because the view is nice.

Scouts become incident reports when they stop reporting terrain and start fighting it.

## AA Live Probe

AA received a passive Logcat-only RECCE probe on branch:

```text
codex/recce-probe-logcat
```

The probe used AA's own terrain vocabulary:

```text
APP_START
AUTH
SESSION
GOVERNANCE
NAVIGATION
```

This was the correct approach. Direct OhBuoy core dependency was technically possible but architecturally premature because it forced OhBuoy V0's starter node vocabulary onto AA.

Terrain must remain sovereign.

OhBuoy learns from terrain instead of pretending it already knows the jungle.

## Live Terrain Read

First emulator runtime trace:

```text
APP_START ✓
NAVIGATION ⛔
AUTH ⛔
```

Interpretation:

Navigation can react to initial `Unauthenticated` state before Firebase auth listener reports `currentUser = null`.

That is not a probe failure. That is terrain speaking.

## AA Drift Found

Physical device login showed topology drift across AA, Firebase, and the wider SGP evolution.

Observed terrain:

```text
AUTH attempt ⛔
SESSION hydration ⛔
NAVIGATION ⛔
```

Known concrete signal:

```text
Firestore PERMISSION_DENIED: Missing or insufficient permissions.
```

Important limitation found:

Manual login failure currently logs only `success=false`; the Firebase auth error message is not surfaced clearly enough.

Next AA work should be boring and surgical:

- surface Firebase auth failure reason in logs
- identify failing UID
- inspect Firestore rules and user document shape
- preserve the passive probe discipline

## Future Layer

The future mapping layer is named:

```text
Plotter
```

Plotter is the position instrument: it asks "Where am I?" in local node vocabulary and corridors. It is not part of OhBuoy V0.

Current separation:

```text
OhBuoy = continuity instrument, asks "Is the route alive?"
Compass = bearing instrument, asks "Which way should I face?"
RECCE = reconnaissance instrument, asks "What is actually out there?"
Plotter = position instrument, asks "Where am I?"
AIluminode = posture-before-action orientation instrument, asks "How should I proceed?"
EEG = internal AIluminode scan mode for route polarity
nai = larger cognition layer
```

AIluminode is an external orientation instrument for prompts/tasks. Compass can guide AIluminode's internal EEG mode when a declared propagation corridor is known, then that mode emits active terrain, stance, route polarity, drift risks, and the next safe action. Route polarity can mark routes as `OPEN`, `PROTECT`, `BLOCK`, `DEFER`, or `AUDIT`.

Compass determines corridor truth. AIluminode determines posture before action.

AIluminode does not crawl, retain prompts, retrieve memory, ingest source material, or change code.

Glass Stack doctrine:

```text
No pane owns the terrain.
No pane owns another pane.
Use the smallest set of panes needed before action.
```

Unknown terrain:

```text
unknownTerrain = route not registered, position uncertain, bearing unavailable.
Stop propagation and report state.
```

Do not add marker runtime, persistence, dashboards, autonomous discovery, or memory systems to OhBuoy V0.

The buoy floats because it is light.
