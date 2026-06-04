package io.nodaiity.ohbuoy.core.eeg

data class RouteDecision(
    val route: String,
    val polarity: RoutePolarity,
    val reason: String
)
