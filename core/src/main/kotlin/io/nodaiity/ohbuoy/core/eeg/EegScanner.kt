package io.nodaiity.ohbuoy.core.eeg

import io.nodaiity.ohbuoy.core.compass.Compass
import io.nodaiity.ohbuoy.core.compass.CompassMode

class EegScanner {

    fun scan(prompt: String): EegReport {
        val normalizedPrompt = prompt.trim()
        val lowerPrompt = normalizedPrompt.lowercase()

        val activeTerrain = detectTerrain(lowerPrompt)
        val stance = detectStance(lowerPrompt, activeTerrain)
        val compassReport = compassReportFor(activeTerrain)
        val routeDecisions = detectRouteDecisions(lowerPrompt, activeTerrain, compassReport?.codexTargets.orEmpty())
        val driftRisks = detectDriftRisks(lowerPrompt, activeTerrain)

        return EegReport(
            traceId = "eeg_${stableTraceSuffix(normalizedPrompt)}",
            activeTerrain = activeTerrain,
            stance = stance,
            compassGuidance = compassGuidance(compassReport),
            routeDecisions = routeDecisions,
            openRoutes = routesFor(routeDecisions, RoutePolarity.OPEN),
            deferRoutes = routesFor(routeDecisions, RoutePolarity.DEFER),
            blockedRoutes = routesFor(routeDecisions, RoutePolarity.BLOCK),
            protectedRoutes = routesFor(routeDecisions, RoutePolarity.PROTECT),
            auditRoutes = routesFor(routeDecisions, RoutePolarity.AUDIT),
            driftRisks = driftRisks,
            nextSafeAction = nextSafeAction(stance, activeTerrain, driftRisks, routeDecisions)
        )
    }

    private fun detectTerrain(prompt: String): List<String> {
        val terrain = mutableListOf<String>()

        if (prompt.containsAny("paula", "memory", "sqlite", "vector", "chroma", "logs")) {
            terrain += "paula_memory_pipeline"
        }
        if (prompt.containsAny("topography", "terrain", "switchbox", "airlock", "orientation")) {
            terrain += "contextual_topography"
        }
        if (prompt.containsAny("ohbuoy", "recce", "plotter", "s+r", "search and rescue")) {
            terrain += "nodaiity_s+r"
        }
        if (prompt.containsAny("epub", "book", "chapter", "section", "source", "shared", "books")) {
            terrain += "source_terrain"
        }
        if (prompt.containsAny("code", "refactor", "test", "compile", ".kt", ".py", "repo")) {
            terrain += "codebase"
        }

        return terrain.ifEmpty { listOf("general_context") }.distinct()
    }

    private fun detectStance(prompt: String, terrain: List<String>): String {
        return when {
            prompt.containsAny("audit", "check", "inspect", "trace", "recce") -> "reconnaissance"
            prompt.containsAny("fix", "refactor", "patch", "remove", "wire", "add") -> "surgical_refactor"
            prompt.containsAny("explain", "what happens", "possibilities", "outcomes") -> "explainer"
            "source_terrain" in terrain -> "bounded_source_reader"
            else -> "orientation"
        }
    }

    private fun detectRouteDecisions(
        prompt: String,
        terrain: List<String>,
        compassTargets: List<String>
    ): List<RouteDecision> {
        val decisions = mutableListOf<RouteDecision>()

        decisions += route("current_prompt", RoutePolarity.OPEN, "declared task is the active entry point")

        if ("paula_memory_pipeline" in terrain) {
            decisions += route("paula_memory_files", RoutePolarity.OPEN, "Paula memory terrain is active")
        }
        if ("contextual_topography" in terrain) {
            decisions += route("topology_packet", RoutePolarity.OPEN, "topography or route language is active")
        }
        if ("nodaiity_s+r" in terrain) {
            decisions += route("s+r_doctrine", RoutePolarity.OPEN, "S+R doctrine is explicitly named")
        }
        if ("source_terrain" in terrain) {
            decisions += route("bounded_source_index", RoutePolarity.OPEN, "source terrain is active; use bounded source access")
        }
        if ("codebase" in terrain) {
            decisions += route("declared_code_surface", RoutePolarity.OPEN, "code work is explicitly requested")
        }

        decisions += memoryRoute(prompt, "saved_memory", "save", "saving", "saved memory")
        decisions += memoryRoute(prompt, "vector_memory", "vector", "learn", "ingest")
        decisions += archiveRoute(prompt)
        decisions += fullSourceRoute(prompt)
        decisions += dashboardRoute(prompt)
        compassTargets.forEach { target ->
            decisions += route(
                route = "compass:${target.replace(" ", "_")}",
                polarity = RoutePolarity.AUDIT,
                reason = "Compass target for observed propagation drift"
            )
        }

        decisions += route("autonomous_crawling", RoutePolarity.BLOCK, "EEG V1 stays declared-terrain only")
        decisions += route("telemetry_empire", RoutePolarity.BLOCK, "no telemetry, retention, or dashboard drift")

        if ("source_terrain" !in terrain) {
            decisions += route("literary_terrain", RoutePolarity.BLOCK, "book/source terrain is not active")
        }
        if (!prompt.containsAny("creative", "roleplay", "fiction", "book")) {
            decisions += route("creative_immersion", RoutePolarity.BLOCK, "creative terrain was not requested")
        }
        if (prompt.containsAny("rewrite from scratch", "rebuild")) {
            decisions += route("full_rewrite", RoutePolarity.AUDIT, "large rewrite language requires explicit confirmation")
        } else {
            decisions += route("full_rewrite", RoutePolarity.BLOCK, "preserve surgical change boundaries")
        }

        return decisions.distinctBy { it.route to it.polarity }
    }

    private fun detectDriftRisks(prompt: String, terrain: List<String>): List<String> {
        val risks = mutableListOf<String>()

        if ("source_terrain" in terrain) {
            risks += "source_terrain_mistaken_for_memory"
        }
        if ("paula_memory_pipeline" in terrain && "source_terrain" in terrain) {
            risks += "book_context_bleeding_into_primary_continuity"
        }
        if (prompt.containsAny("personality", "stance", "identity", "who am i")) {
            risks += "stance_identity_overlap"
        }
        if (prompt.containsAny("logs", "archive", "recent")) {
            risks += "archive_recent_memory_confusion"
        }
        if (prompt.containsAny("chapter", "section")) {
            risks += "section_numbers_mistaken_for_chapters"
        }
        if (risks.isEmpty()) {
            risks += "low_observed_drift"
        }

        return risks.distinct()
    }

    private fun nextSafeAction(
        stance: String,
        terrain: List<String>,
        risks: List<String>,
        routeDecisions: List<RouteDecision>
    ): String {
        return when {
            "section_numbers_mistaken_for_chapters" in risks -> "List source sections or search source text before summarizing."
            routeDecisions.any { it.route == "saved_memory" && it.polarity == RoutePolarity.PROTECT } ->
                "Protect saved memory; inspect adjacent route logic without modifying it."
            "source_terrain_mistaken_for_memory" in risks -> "Open bounded source snippets; do not save or ingest unless asked."
            stance == "surgical_refactor" -> "Inspect the declared files first, then make the smallest route-preserving patch."
            stance == "reconnaissance" -> "Observe and report expected vs observed terrain before changing behavior."
            "codebase" in terrain -> "Read the local implementation surface before editing."
            else -> "Return a compact orientation packet before acting."
        }
    }

    private fun stableTraceSuffix(prompt: String): String {
        if (prompt.isBlank()) return "0000"
        val hash = prompt.fold(17) { acc, char -> (acc * 31 + char.code) and 0x7fffffff }
        return hash.toString().takeLast(4).padStart(4, '0')
    }

    private fun routesFor(decisions: List<RouteDecision>, polarity: RoutePolarity): List<String> {
        return decisions.filter { it.polarity == polarity }.map { it.route }.distinct()
    }

    private fun compassReportFor(terrain: List<String>) =
        if ("paula_memory_pipeline" in terrain) {
            Compass().run(CompassMode.PAULA_MEMORY_PIPELINE)
        } else {
            null
        }

    private fun compassGuidance(report: io.nodaiity.ohbuoy.core.compass.CompassReport?): List<String> {
        if (report == null) return emptyList()
        return listOf(
            "route=${report.routeId}",
            "howler=${report.howler}",
            "likely_fix=${report.likelyFix}",
            "codex_targets=${report.codexTargets.joinToString(",")}"
        )
    }

    private fun memoryRoute(prompt: String, route: String, vararg markers: String): RouteDecision {
        val touched = prompt.containsAny(*markers)
        val protected = touched && prompt.hasProtectionNearAny(*markers)
        return when {
            protected -> route(route, RoutePolarity.PROTECT, "$route is named under a protection/avoidance phrase")
            touched -> route(route, RoutePolarity.OPEN, "$route is explicitly requested or relevant")
            else -> route(route, RoutePolarity.DEFER, "$route is not needed for this task")
        }
    }

    private fun archiveRoute(prompt: String): RouteDecision {
        val touched = prompt.containsAny("logs", "archive")
        val protected = touched && prompt.hasProtectionNearAny("logs", "archive")
        return when {
            protected -> route("archive_logs", RoutePolarity.PROTECT, "archive/log route is named under protection")
            touched -> route("archive_logs", RoutePolarity.AUDIT, "archive/log route should be inspected as evidence")
            else -> route("archive_logs", RoutePolarity.DEFER, "archive route not requested")
        }
    }

    private fun fullSourceRoute(prompt: String): RouteDecision {
        val touched = prompt.containsAny("full book", "whole book", "full source", "all sections")
        val protected = touched && prompt.hasProtectionNearAny("full book", "whole book", "full source", "all sections")
        return when {
            protected -> route("full_source_ingestion", RoutePolarity.PROTECT, "full-source ingestion is explicitly avoided")
            touched -> route("full_source_ingestion", RoutePolarity.AUDIT, "full-source ingestion requires confirmation")
            else -> route("full_source_ingestion", RoutePolarity.DEFER, "bounded source access is safer")
        }
    }

    private fun dashboardRoute(prompt: String): RouteDecision {
        val touched = prompt.containsAny("dashboard", "ui")
        val protected = touched && prompt.hasProtectionNearAny("dashboard", "ui")
        return when {
            protected -> route("dashboard", RoutePolarity.PROTECT, "dashboard/UI route is explicitly avoided")
            touched -> route("dashboard", RoutePolarity.AUDIT, "dashboard/UI route requires scope confirmation")
            else -> route("dashboard", RoutePolarity.DEFER, "dashboard not requested")
        }
    }

    private fun route(route: String, polarity: RoutePolarity, reason: String): RouteDecision {
        return RouteDecision(route = route, polarity = polarity, reason = reason)
    }

    private fun String.containsAny(vararg needles: String): Boolean {
        return needles.any { contains(it) }
    }

    private fun String.hasProtectionNearAny(vararg needles: String): Boolean {
        return needles.any { needle ->
            val index = indexOf(needle)
            if (index < 0) return@any false
            val start = (index - 42).coerceAtLeast(0)
            val end = (index + needle.length + 24).coerceAtMost(length)
            substring(start, end).containsAny(
                "without",
                "do not",
                "don't",
                "avoid",
                "protect",
                "leave",
                "preserve",
                "no "
            )
        }
    }
}
