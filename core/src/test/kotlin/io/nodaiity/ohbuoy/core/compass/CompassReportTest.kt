package io.nodaiity.ohbuoy.core.compass

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CompassReportTest {

    private val compass = Compass()
    private val renderer = CompassRenderer()

    @Test
    fun paulaMemoryPipelineReportsDeclaredPropagationDrift() {
        val report = compass.run(CompassMode.PAULA_MEMORY_PIPELINE)
        val renderedReport = renderer.render(report)

        assertEquals("paula_memory_pipeline", report.routeId)
        assertEquals("USER_INPUT", report.expectedRoute.first())
        assertEquals("SQLITE_MEMORY_WRITE", report.expectedRoute.last())
        assertTrue(report.observedRoute.contains("RECENT_SQLITE_MEMORY"))
        assertEquals("RECENT_SQLITE_MEMORY bypasses STANCE_GATE and TERRAIN_GATE.", report.howler)

        assertTrue(renderedReport.contains("COMPASS REPORT paula_memory_pipeline"))
        assertTrue(renderedReport.contains("STANCE_GATE"))
        assertTrue(renderedReport.contains("RECENT_SQLITE_MEMORY"))
        assertTrue(renderedReport.contains("LIKELY FIX:"))
        assertTrue(renderedReport.contains("- prompt assembly"))
    }
}
