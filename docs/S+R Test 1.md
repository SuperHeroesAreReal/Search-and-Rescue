# S+R Test 1

Search and Rescue (S+R) test for the full NodAIity orientation stack, using Paula as the primary pressure surface.

Scope:

- Branch A orientation validation
- Paula orientation and recovery
- corridor fidelity
- side-door awareness
- reorientation cost

Out of scope for Test 1:

- retrieval-speed optimization
- grep/file-search savings
- Branch B
- Context Layer
- Kaleidoscope / KL
- live memory expansion

## Core Question

The original Paula pressure was not:

```text
Is retrieval faster?
```

It was:

```text
Is Paula better oriented?
```

That distinction matters.

If Branch A is truly `Orientation Before Retrieval`, the first measurable value should be:

```text
Where am I?
Which way should I face?
What terrain is active?
```

Only after orientation is proven should retrieval savings become the primary benchmark.

## Branch A Under Test

```text
Prompt
↓
Stance
↓
Compass
↓
Plotter
↓
AIluminode
↓
Retrieval
↓
Reasoning
↓
Response
```

Toolkit roles in this test:

```text
OhBuoy     = route-alive continuity signal
RECCE      = bounded observation
Compass    = bearing, asks "Which way should I face?"
Plotter    = position, asks "Where am I?"
AIluminode = posture/orientation packet, asks "How should I proceed?"
```

## Phase 1

Prove orientation.

Primary success criteria:

- correct terrain activation
- corridor fidelity
- drift resistance
- lower reorientation cost
- useful side-door awareness without corridor switching

Secondary success criteria:

- fewer unnecessary retrieval steps
- fewer memory scans
- fewer route switches

## Test 1: Reorientation After Compaction

Purpose:

Measure whether Branch A helps Paula recover position after context compaction.

Comparison:

```text
Without Branch A:
How long does Paula take to recover position after compaction?

With Branch A:
How long does Paula take to recover position after compaction?
```

Success metric:

```text
Correct terrain activation
```

Not:

```text
retrieval count
```

Record:

- active terrain selected
- bearing selected
- position selected
- orientation packet produced
- number of turns needed to recover
- whether Paula resumes the correct work corridor

## Test 2: Wrong-Corridor Resistance

Purpose:

Measure whether Paula remains in the intended terrain when adjacent terrain is tempting.

Prompt shape:

```text
Continue Paula work.
```

Known failure patterns:

```text
Paula -> Pauline
Paula -> Swan
Plotter -> fiction / literary terrain
RECCE -> unrelated military doctrine
```

Success metric:

```text
Corridor fidelity
```

Record:

- intended corridor
- adjacent corridor attempted
- whether blocked/deferred terrain stayed dormant
- whether response stayed in Paula/NodAIity terrain

## Test 3: Side-Door Awareness

Purpose:

Measure whether Paula can notice relevant adjacent terrain without switching corridors.

Example:

```text
Active terrain:
EBOS

Adjacent terrain:
Delivery
Pricing

Blocked terrain:
AA
SGP
```

Success metric:

```text
Awareness without drift
```

Record:

- active terrain
- peripheral terrain
- deferred terrain
- denied or blocked terrain
- whether Paula reports adjacency without implementing from it

## Test 4: Reorientation Cost

Purpose:

Measure how much work is required to figure out where Paula is.

This is an orientation-efficiency test.

Measure:

- correct active terrain?
- correct bearing?
- correct position?
- number of route checks
- number of memory scans
- number of terrain switches
- number of turns to regain stable response

Success metric:

```text
Reduced work required to establish position and bearing
```

This is not raw retrieval speed.

## Deferred Benchmark Family: Codex Retrieval Metrics

The Codex metrics are a separate benchmark family.

They came later as practical engineering proof.

Measure:

- grep count
- files opened
- pages scanned
- search iterations
- wrong-corridor search
- re-grepping
- retrieval work

These are valuable, but they belong after the Paula orientation tests.

## Research Story

Phase 1:

```text
Prove orientation.
```

Phase 2:

```text
Measure retrieval savings.
```

This preserves the core claim:

```text
Orientation has measurable value before retrieval begins.
```

## Expected Outcome

If Branch A works, Paula should:

- recover faster after compaction
- select the correct active terrain sooner
- resist adjacent terrain hijack
- preserve useful side doors without switching corridors
- require fewer route checks to regain position
- produce better-grounded responses before retrieval expands

The first win is not faster retrieval.

The first win is better orientation.

