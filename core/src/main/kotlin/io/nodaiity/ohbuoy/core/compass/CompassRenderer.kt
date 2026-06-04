package io.nodaiity.ohbuoy.core.compass

class CompassRenderer {

    fun render(report: CompassReport): String {
        return buildString {
            appendLine("COMPASS REPORT ${report.routeId}")
            appendLine()
            appendLine("EXPECTED:")
            appendLine(report.expectedRoute.joinToString(separator = "\n→ "))
            appendLine()
            appendLine("OBSERVED:")
            appendLine(report.observedRoute.joinToString(separator = "\n→ "))
            appendLine()
            appendLine("HOWLER:")
            appendLine(report.howler)
            appendLine()
            appendLine("LIKELY FIX:")
            appendLine(report.likelyFix)
            appendLine()
            appendLine("CODEX TARGETS:")
            append(report.codexTargets.joinToString(separator = "\n") { target -> "- $target" })
        }
    }
}
