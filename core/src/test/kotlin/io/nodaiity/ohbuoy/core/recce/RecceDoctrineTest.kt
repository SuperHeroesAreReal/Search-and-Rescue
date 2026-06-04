package io.nodaiity.ohbuoy.core.recce

import io.nodaiity.ohbuoy.core.NodeId
import io.nodaiity.ohbuoy.core.NodeState
import io.nodaiity.ohbuoy.core.render.TraceRenderer
import io.nodaiity.ohbuoy.core.scanner.ContinuityScanner
import io.nodaiity.ohbuoy.core.scanner.ScannerMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RecceDoctrineTest {

    private val scanner = ContinuityScanner()
    private val renderer = TraceRenderer()

    @Test
    fun startupTraceReportsContinuityTerrain() {
        val packets = scanner.run(ScannerMode.STARTUP)
        val renderedTrace = renderer.render(packets)

        assertEquals(4, packets.size)
        assertEquals(NodeId.AUTH_NODE, packets[0].nodeId)
        assertEquals(NodeState.OK, packets[0].state)
        assertEquals(NodeId.TENANT_NODE, packets[1].nodeId)
        assertEquals(NodeState.OK, packets[1].state)
        assertEquals(NodeId.GOVERNANCE_NODE, packets[2].nodeId)
        assertEquals(NodeState.WARN, packets[2].state)
        assertEquals(NodeId.NAVIGATION_NODE, packets[3].nodeId)
        assertEquals(NodeState.STOP, packets[3].state)

        assertTrue(renderedTrace.contains("TRACE startup_4812"))
        assertTrue(renderedTrace.contains("AUTH ✓"))
        assertTrue(renderedTrace.contains("TENANT ✓"))
        assertTrue(renderedTrace.contains("GOVERNANCE ⚠"))
        assertTrue(renderedTrace.contains("NAVIGATION ⛔"))
    }

    @Test
    fun governanceTraceMarksPropagationRiskWithoutResolvingIt() {
        val packets = scanner.run(ScannerMode.GOVERNANCE)
        val governancePacket = packets.first { it.nodeId == NodeId.GOVERNANCE_NODE }
        val navigationPacket = packets.first { it.nodeId == NodeId.NAVIGATION_NODE }
        val renderedTrace = renderer.render(packets)

        assertEquals(NodeState.WARN, governancePacket.state)
        assertEquals("Governance policy uncertainty propagated downstream.", governancePacket.downstreamEffect)
        assertEquals(NodeState.WARN, navigationPacket.state)
        assertTrue(renderedTrace.contains("HOWLER:"))
        assertTrue(renderedTrace.contains("Governance policy uncertainty propagated downstream."))
    }
}
