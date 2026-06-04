package io.nodaiity.ohbuoy.core.eeg

class EegRenderer {

    fun render(report: EegReport): String {
        return buildString {
            appendLine("EEG TRACE ${report.traceId}")
            appendLine()
            appendLine("ACTIVE_TERRAIN:")
            appendBullets(report.activeTerrain)
            appendLine()
            appendLine("STANCE:")
            appendLine(report.stance)
            appendLine()
            appendLine("COMPASS_GUIDANCE:")
            appendBullets(report.compassGuidance)
            appendLine()
            appendLine("ROUTE_POLARITY:")
            appendRouteDecisions(report.routeDecisions)
            appendLine()
            appendLine("OPEN_ROUTES:")
            appendBullets(report.openRoutes)
            appendLine()
            appendLine("PROTECT:")
            appendBullets(report.protectedRoutes)
            appendLine()
            appendLine("AUDIT:")
            appendBullets(report.auditRoutes)
            appendLine()
            appendLine("DEFER:")
            appendBullets(report.deferRoutes)
            appendLine()
            appendLine("BLOCK:")
            appendBullets(report.blockedRoutes)
            appendLine()
            appendLine("DRIFT_RISK:")
            appendBullets(report.driftRisks)
            appendLine()
            appendLine("NEXT_SAFE_ACTION:")
            append(report.nextSafeAction)
        }
    }

    private fun StringBuilder.appendRouteDecisions(values: List<RouteDecision>) {
        values.forEach { decision ->
            appendLine("- ${decision.polarity}: ${decision.route} (${decision.reason})")
        }
    }

    private fun StringBuilder.appendBullets(values: List<String>) {
        if (values.isEmpty()) {
            appendLine("- none")
        } else {
            values.forEach { value ->
                appendLine("- $value")
            }
        }
    }
}
