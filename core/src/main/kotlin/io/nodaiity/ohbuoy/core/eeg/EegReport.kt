package io.nodaiity.ohbuoy.core.eeg

data class EegReport(
    val traceId: String,
    val activeTerrain: List<String>,
    val stance: String,
    val compassGuidance: List<String>,
    val routeDecisions: List<RouteDecision>,
    val openRoutes: List<String>,
    val deferRoutes: List<String>,
    val blockedRoutes: List<String>,
    val protectedRoutes: List<String>,
    val auditRoutes: List<String>,
    val driftRisks: List<String>,
    val nextSafeAction: String
)
