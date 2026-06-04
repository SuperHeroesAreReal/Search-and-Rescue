package io.nodaiity.ohbuoy.core.compass

class Compass {

    fun run(mode: CompassMode): CompassReport {
        return when (mode) {
            CompassMode.PAULA_MEMORY_PIPELINE -> paulaMemoryPipeline()
        }
    }

    private fun paulaMemoryPipeline(): CompassReport {
        return CompassReport(
            routeId = "paula_memory_pipeline",
            expectedRoute = listOf(
                "USER_INPUT",
                "STANCE_GATE",
                "TERRAIN_GATE",
                "BLOCKERS/SIGNPOSTS",
                "FILTERED_RECENT_MEMORY",
                "BOUNDED_RETRIEVAL",
                "TOPOGRAPHY_PACKET",
                "MODEL_PROMPT",
                "MODEL_RESPONSE",
                "SQLITE_MEMORY_WRITE"
            ),
            observedRoute = listOf(
                "USER_INPUT",
                "TOPOGRAPHY_PACKET",
                "RECENT_SQLITE_MEMORY",
                "MODEL_PROMPT",
                "MODEL_RESPONSE",
                "SQLITE_MEMORY_WRITE"
            ),
            howler = "RECENT_SQLITE_MEMORY bypasses STANCE_GATE and TERRAIN_GATE.",
            likelyFix = "Move recent memory retrieval behind topology filtering.",
            codexTargets = listOf(
                "prompt assembly",
                "recent conversation loader",
                "sqlite retrieval function",
                "topography packet builder"
            )
        )
    }
}
