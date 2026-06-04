# S+R Test 3

Search and Rescue (S+R) Phase 3 benchmark: doctrine propagation and orientation transferability.

Phase 1:

```text
Orientation exists.
```

Phase 2:

```text
Orientation saves work.
```

Phase 3:

```text
Orientation can be transmitted.
```

## Core Question

Can clean doctrine reduce orientation cost for both humans and AI?

This test is not about retrieval speed.

It is about whether a compact orientation doctrine can be adopted, retained, compressed, and applied by different reasoning surfaces without fine-tuning or retraining.

## Scope

Measure doctrine adoption and boundary preservation across:

- Gemini
- DeepSeek
- Codex
- future models
- humans

Out of scope:

- Context Layer
- Branch B
- KL / Kaleidoscope
- model fine-tuning
- persistent memory systems
- retrieval-speed benchmarking

## Observed Motivation

Informal evidence already appeared:

- Gemini adopted terrain language after Compass explanation.
- DeepSeek adopted terrain language after Toolkit explanation.
- Codex adopted Compass, Plotter, orientation packets, and terrain framing with low resistance.

The test asks whether this can be measured.

## Control

Give no doctrine.

Observe:

- ambiguity
- overlap
- confusion
- invented architecture
- role collapse
- wrong-corridor reasoning

Example control prompt:

```text
Here is a system with memory, retrieval, routing, context, and response behavior.
Explain how to organize it.
```

Record whether the subject independently separates bearing, position, retrieval, truth, and response authority.

## Orientation Condition

Give compact doctrine:

```text
Compass = Bearing = Which way should I face?
Plotter = Position = Where am I?
Truth remains sovereign.
Do not make retrieval run faster.
Make the terrain easier to traverse.
```

Observe:

- terminology adoption
- concept retention
- role separation
- boundary correctness
- reduced invented architecture
- clearer routing language

## Metric 1: Adoption Speed

Question:

```text
How many interactions before the subject uses the doctrine correctly?
```

Measure:

- turn count before correct use of terms
- turn count before correct Compass/Plotter distinction
- turn count before reduced wrong-corridor behavior
- whether the subject uses doctrine spontaneously after initial exposure

Success signal:

The subject begins using:

```text
bearing
position
active terrain
deferred terrain
unknownTerrain
side-door awareness
```

without needing repeated correction.

## Metric 2: Boundary Accuracy

Question:

```text
Does the subject preserve tool and concept boundaries?
```

Test separations:

```text
Compass vs Plotter
Retrieval vs Reasoning
Context vs Truth
KL vs Reasoning
Memory vs Source Terrain
Side-door awareness vs corridor switching
```

Success signal:

The subject can explain and apply each distinction without collapsing one concern into another.

Failure examples:

- Compass becomes position.
- Plotter becomes bearing.
- retrieval becomes truth.
- KL becomes reasoning.
- Context becomes authority.
- side-door awareness becomes implementation drift.

## Metric 3: Compression Survival

Question:

```text
Can the doctrine survive compression?
```

Test:

```text
5 pages
↓
2 pages
↓
1 page
↓
packet
```

Measure:

- what concepts survive
- what concepts drift
- whether Branch A can be reconstructed
- whether boundaries remain correct
- whether unknownTerrain remains first-class

Success signal:

The subject can reconstruct:

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

from a compact packet without inventing missing layers.

## Metric 4: Transferability

Question:

```text
Does doctrine transfer across subjects?
```

Compare:

- Gemini
- DeepSeek
- Codex
- humans
- future models

Measure:

- which terms transfer cleanly
- which boundaries need reinforcement
- which subjects invent extra layers
- which subjects preserve the smallest useful stack

## Research Story

Phase 1:

```text
Prove orientation.
```

Phase 2:

```text
Measure work avoided.
```

Phase 3:

```text
Measure orientation transferability.
```

If Phase 3 succeeds, Branch A does not require fine-tuning, retraining, or giant infrastructure to be useful.

It becomes:

```text
cognitive positioning doctrine
```

## Hypothesis

Good doctrine acts as semantic compression.

It reduces the amount of context needed to orient a subject because the doctrine carries:

- roles
- boundaries
- order
- purpose
- failure modes

## Expected Outcome

If doctrine propagation works, subjects should:

- adopt the vocabulary quickly
- preserve Compass/Plotter separation
- reduce wrong-corridor reasoning
- reconstruct Branch A from compact packets
- avoid making Context, KL, retrieval, or memory sovereign
- apply orientation before retrieval or reasoning

The first win is better orientation.

The second win is work avoided.

The third win is orientation that can be transmitted.

