package io.nodaiity.ohbuoy.core.compass

data class CompassReport(
    val routeId: String,
    val expectedRoute: List<String>,
    val observedRoute: List<String>,
    val howler: String,
    val likelyFix: String,
    val codexTargets: List<String>
)
