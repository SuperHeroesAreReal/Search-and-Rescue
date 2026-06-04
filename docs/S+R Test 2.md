# S+R Test 2

Search and Rescue (S+R) Phase 2 benchmark: Codex retrieval-work reduction.

Phase 1 proved the first question:

```text
Does orientation improve terrain activation, bearing, position, and corridor fidelity?
```

Phase 2 asks the operational question:

```text
What work never happened because orientation happened first?
```

## Scope

Use Codex as the first benchmark surface.

Measure retrieval and search work during coding/repo tasks.

In scope:

- grep count
- files opened
- pages or chunks scanned
- search iterations
- wrong-corridor searches
- re-grepping
- useful result density

Out of scope:

- proving Branch A orientation itself
- Paula memory behavior
- GUI/runtime tooling
- telemetry systems
- dashboards
- autonomous crawling

## Research Story

Phase 1:

```text
Orientation exists.
```

Questions:

```text
Where am I?
Which way should I face?
What terrain is active?
```

Phase 2:

```text
Orientation saves work.
```

Questions:

```text
How much work?
What search did not happen?
What wrong corridor was avoided?
```

## Control Flow

Baseline task without Branch A orientation:

```text
Task
↓
Search
↓
Retrieve
↓
Implement
```

Record:

- grep count
- files opened
- pages or chunks scanned
- searches performed
- route changes
- wrong-corridor searches
- repeated searches

## Orientation Flow

Task with Branch A orientation:

```text
Task
↓
Stance
↓
Compass
↓
Plotter
↓
AIluminode
↓
Search
↓
Retrieve
↓
Implement
```

Record the same metrics.

## Primary Metrics

### Search Noise Reduction

Measure:

```text
relevant results examined
÷
total results examined
```

Higher is better.

Interpretation:

Branch A should increase the percentage of examined results that actually matter.

### Re-Grepping Reduction

Measure:

```text
number of repeated searches for the same target
```

Lower is better.

Interpretation:

Better orientation should reduce rediscovery loops.

### Wrong-Corridor Search

Measure:

```text
searches performed in terrain that should have been deferred, blocked, or peripheral
```

Lower is better.

Example:

```text
Task:
Fix AA onboarding.

Expected active terrain:
AA onboarding/session corridor.

Wrong-corridor examples:
SGP UI cleanup
EBOS logistics
unrelated Firebase rules
random utility searches
```

### Useful Result Density

Measure:

```text
useful findings
÷
total search effort
```

Higher is better.

This is the user-feel metric.

Users do not care how elegant the search was. They feel whether the agent is wandering.

## Supporting Metrics

Record:

- first useful file found at search number N
- first correct corridor identified at step N
- number of files opened before first useful edit
- number of discarded search branches
- number of times the agent changed interpretation of the task
- number of times the agent returned to a previously searched term

## Minimal Benchmark Record

For each task:

```text
Task:

Mode:
CONTROL or ORIENTATION

Declared active terrain:

Deferred terrain:

Blocked terrain:

Search count:

Files opened:

Useful files opened:

Wrong-corridor searches:

Repeated searches:

First useful file at step:

Useful result density:

Notes:
```

## Expected Outcome

If Branch A works operationally, Codex should show:

- fewer broad searches
- fewer repeated searches
- fewer wrong-corridor searches
- earlier discovery of useful files
- higher useful result density
- less need to reread unrelated terrain

## Relationship To Test 1

Test 1 proves the first-order effect:

```text
better orientation
```

Test 2 measures the second-order effect:

```text
less wasted retrieval work
```

Expected chain:

```text
Better orientation
↓
Fewer wrong corridors
↓
Less retrieval waste
↓
Higher useful result density
```

The first win is better orientation.

The second win is work avoided.

