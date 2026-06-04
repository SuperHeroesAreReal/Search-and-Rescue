package io.nodaiity.ohbuoy.cli

import io.nodaiity.ohbuoy.core.compass.Compass
import io.nodaiity.ohbuoy.core.compass.CompassMode
import io.nodaiity.ohbuoy.core.compass.CompassRenderer
import io.nodaiity.ohbuoy.core.eeg.EegRenderer
import io.nodaiity.ohbuoy.core.eeg.EegScanner
import io.nodaiity.ohbuoy.core.render.TraceRenderer
import io.nodaiity.ohbuoy.core.scanner.ContinuityScanner
import io.nodaiity.ohbuoy.core.scanner.ScannerMode
import java.io.PrintStream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    System.setOut(PrintStream(System.out, true, Charsets.UTF_8))

    val compassMode = args.toCompassMode()
    if (compassMode != null) {
        val report = Compass().run(compassMode)
        println(CompassRenderer().render(report))
        return
    }

    if (args.firstOrNull()?.equals("eeg", ignoreCase = true) == true) {
        val prompt = args.drop(1).joinToString(" ").trim()
        if (prompt.isBlank()) {
            println("Usage: ohbuoy eeg <prompt>")
            exitProcess(1)
        }
        val report = EegScanner().scan(prompt)
        println(EegRenderer().render(report))
        return
    }

    val mode = args.toScannerMode()

    if (mode == null) {
        println("Usage: ohbuoy [scan] <startup|login|governance|navigation>")
        println("       ohbuoy compass <paula_memory_pipeline>")
        println("       ohbuoy eeg <prompt>")
        exitProcess(1)
    }

    val packets = ContinuityScanner().run(mode)
    println(TraceRenderer().render(packets))
}

private fun Array<String>.toScannerMode(): ScannerMode? {
    val modeName = when {
        size == 1 -> this[0]
        size == 2 && this[0].equals("scan", ignoreCase = true) -> this[1]
        else -> return null
    }

    return when (modeName.lowercase()) {
        "startup" -> ScannerMode.STARTUP
        "login" -> ScannerMode.LOGIN
        "governance" -> ScannerMode.GOVERNANCE
        "navigation" -> ScannerMode.NAVIGATION
        else -> null
    }
}

private fun Array<String>.toCompassMode(): CompassMode? {
    if (size != 2 || !this[0].equals("compass", ignoreCase = true)) {
        return null
    }

    return when (this[1].lowercase()) {
        "paula", "paula_memory", "paula_memory_pipeline" -> CompassMode.PAULA_MEMORY_PIPELINE
        else -> null
    }
}
