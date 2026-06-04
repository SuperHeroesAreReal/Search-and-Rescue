package io.nodaiity.ohbuoy.core.scanner

import io.nodaiity.ohbuoy.core.NodeId
import io.nodaiity.ohbuoy.core.NodePacket
import io.nodaiity.ohbuoy.core.NodeState

class ContinuityScanner {

    fun run(mode: ScannerMode): List<NodePacket> {
        return when (mode) {
            ScannerMode.STARTUP -> runStartupTrace()
            ScannerMode.LOGIN -> runLoginTrace()
            ScannerMode.GOVERNANCE -> runGovernanceTrace()
            ScannerMode.NAVIGATION -> runNavigationTrace()
        }
    }

    fun runStartupTrace(): List<NodePacket> {
        val traceId = "startup_4812"
        val timestamp = System.currentTimeMillis()

        return listOf(
            packet(traceId, NodeId.AUTH_NODE, NodeState.OK, "Auth continuity intact.", timestamp),
            packet(traceId, NodeId.TENANT_NODE, NodeState.OK, "Tenant continuity intact.", timestamp, NodeId.AUTH_NODE),
            packet(
                traceId = traceId,
                nodeId = NodeId.GOVERNANCE_NODE,
                state = NodeState.WARN,
                message = "Governance continuity degraded.",
                timestamp = timestamp,
                upstreamNode = NodeId.TENANT_NODE,
                downstreamEffect = "Governance degradation propagated downstream."
            ),
            packet(traceId, NodeId.NAVIGATION_NODE, NodeState.STOP, "Navigation continuity interrupted.", timestamp, NodeId.GOVERNANCE_NODE)
        )
    }

    private fun runLoginTrace(): List<NodePacket> {
        val traceId = "login_4812"
        val timestamp = System.currentTimeMillis()

        return listOf(
            packet(traceId, NodeId.AUTH_NODE, NodeState.OK, "Auth continuity intact.", timestamp),
            packet(traceId, NodeId.TENANT_NODE, NodeState.OK, "Tenant continuity intact.", timestamp, NodeId.AUTH_NODE),
            packet(traceId, NodeId.GOVERNANCE_NODE, NodeState.OK, "Governance continuity intact.", timestamp, NodeId.TENANT_NODE),
            packet(traceId, NodeId.NAVIGATION_NODE, NodeState.OK, "Navigation continuity intact.", timestamp, NodeId.GOVERNANCE_NODE)
        )
    }

    private fun runGovernanceTrace(): List<NodePacket> {
        val traceId = "governance_4812"
        val timestamp = System.currentTimeMillis()

        return listOf(
            packet(traceId, NodeId.AUTH_NODE, NodeState.OK, "Auth continuity intact.", timestamp),
            packet(traceId, NodeId.TENANT_NODE, NodeState.OK, "Tenant continuity intact.", timestamp, NodeId.AUTH_NODE),
            packet(
                traceId = traceId,
                nodeId = NodeId.GOVERNANCE_NODE,
                state = NodeState.WARN,
                message = "Governance continuity degraded.",
                timestamp = timestamp,
                upstreamNode = NodeId.TENANT_NODE,
                downstreamEffect = "Governance policy uncertainty propagated downstream."
            ),
            packet(traceId, NodeId.NAVIGATION_NODE, NodeState.WARN, "Navigation continuity degraded.", timestamp, NodeId.GOVERNANCE_NODE)
        )
    }

    private fun runNavigationTrace(): List<NodePacket> {
        val traceId = "navigation_4812"
        val timestamp = System.currentTimeMillis()

        return listOf(
            packet(traceId, NodeId.AUTH_NODE, NodeState.OK, "Auth continuity intact.", timestamp),
            packet(traceId, NodeId.TENANT_NODE, NodeState.OK, "Tenant continuity intact.", timestamp, NodeId.AUTH_NODE),
            packet(traceId, NodeId.GOVERNANCE_NODE, NodeState.OK, "Governance continuity intact.", timestamp, NodeId.TENANT_NODE),
            packet(traceId, NodeId.NAVIGATION_NODE, NodeState.STOP, "Navigation continuity interrupted.", timestamp, NodeId.GOVERNANCE_NODE)
        )
    }

    private fun packet(
        traceId: String,
        nodeId: NodeId,
        state: NodeState,
        message: String,
        timestamp: Long,
        upstreamNode: NodeId? = null,
        downstreamEffect: String? = null
    ): NodePacket {
        return NodePacket(
            traceId = traceId,
            nodeId = nodeId,
            state = state,
            message = message,
            timestamp = timestamp,
            upstreamNode = upstreamNode,
            downstreamEffect = downstreamEffect
        )
    }
}
