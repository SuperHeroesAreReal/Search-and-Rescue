# OhBuoy V2 Compass

Compass is the V2 operational orientation surface.

V0 stays a simple debugging scanner. V1 remains reserved for a future V0 upgrade around declared corridor scans. V2 compares declared expected propagation against observed propagation and emits a repair-oriented report.

Compass is not autonomous analysis, repository crawling, telemetry ingestion, or durable marker storage.

Compass determines corridor truth. When a declared corridor is known, Compass can guide AIluminode's internal EEG mode by providing propagation drift, likely fix, and Codex target routes.

Compass is the bearing instrument: it asks "Which way should I face?"

Unknown terrain is a valid Compass result:

```text
unknownTerrain
-> route not registered
-> position uncertain
-> bearing unavailable
-> stop propagation
-> report state
```

```text
declared route
observed route
propagation drift
HOWLER
release
```

## First Route

```text
paula_memory_pipeline
```

Expected:

```text
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
```

Observed:

```text
USER_INPUT
→ TOPOGRAPHY_PACKET
→ RECENT_SQLITE_MEMORY
→ MODEL_PROMPT
→ MODEL_RESPONSE
→ SQLITE_MEMORY_WRITE
```

HOWLER:

```text
RECENT_SQLITE_MEMORY bypasses STANCE_GATE and TERRAIN_GATE.
```

Likely fix:

```text
Move recent memory retrieval behind topology filtering.
```

Codex targets:

```text
prompt assembly
recent conversation loader
sqlite retrieval function
topography packet builder
```

The compass points. It does not crawl.
