package io.nodaiity.ohbuoy.core.eeg

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EegScannerTest {

    private val scanner = EegScanner()
    private val renderer = EegRenderer()

    @Test
    fun sourcePromptOpensBoundedSourceAndBlocksWholeBookIngestion() {
        val report = scanner.scan("Read airframe.epub section 5 without saving it as memory")

        assertTrue(report.activeTerrain.contains("source_terrain"))
        assertTrue(report.openRoutes.contains("bounded_source_index"))
        assertTrue(report.protectedRoutes.contains("saved_memory"))
        assertTrue(report.deferRoutes.contains("full_source_ingestion"))
        assertTrue(report.driftRisks.contains("source_terrain_mistaken_for_memory"))
        assertTrue(report.driftRisks.contains("section_numbers_mistaken_for_chapters"))
        assertEquals("List source sections or search source text before summarizing.", report.nextSafeAction)
    }

    @Test
    fun paulaMemoryPromptWarnsAboutBookContinuityBleed() {
        val report = scanner.scan("Refactor Paula memory routing around EPUB source terrain")

        assertTrue(report.activeTerrain.contains("paula_memory_pipeline"))
        assertTrue(report.activeTerrain.contains("source_terrain"))
        assertTrue(report.openRoutes.contains("paula_memory_files"))
        assertTrue(report.compassGuidance.any { it.contains("RECENT_SQLITE_MEMORY") })
        assertTrue(report.auditRoutes.contains("compass:prompt_assembly"))
        assertTrue(report.driftRisks.contains("book_context_bleeding_into_primary_continuity"))
    }

    @Test
    fun rendererEmitsCompactEegTrace() {
        val report = scanner.scan("Audit Paula logs without touching vector memory")
        val rendered = renderer.render(report)

        assertTrue(rendered.contains("EEG TRACE eeg_"))
        assertTrue(rendered.contains("ACTIVE_TERRAIN:"))
        assertTrue(rendered.contains("STANCE:"))
        assertTrue(rendered.contains("COMPASS_GUIDANCE:"))
        assertTrue(rendered.contains("ROUTE_POLARITY:"))
        assertTrue(rendered.contains("OPEN_ROUTES:"))
        assertTrue(rendered.contains("PROTECT:"))
        assertTrue(rendered.contains("AUDIT:"))
        assertTrue(rendered.contains("DRIFT_RISK:"))
        assertTrue(rendered.contains("NEXT_SAFE_ACTION:"))
    }
}
