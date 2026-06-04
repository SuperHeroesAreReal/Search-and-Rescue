package io.nodaiity.ohbuoy.core.render

import io.nodaiity.ohbuoy.core.NodeId
import io.nodaiity.ohbuoy.core.NodePacket
import io.nodaiity.ohbuoy.core.NodeState

class TraceRenderer {

    fun render(packets: List<NodePacket>): String {
        if (packets.isEmpty()) {
            return "TRACE none"
        }

        val traceId = packets.first().traceId
        val nodeLines = packets.joinToString(separator = "\n") { packet ->
            "${packet.nodeId.displayName()} ${packet.state.symbol()}"
        }
        val downstreamEffect = packets.firstNotNullOfOrNull { packet ->
            packet.downstreamEffect
        }

        return buildString {
            appendLine("TRACE $traceId")
            appendLine()
            appendLine(nodeLines)

            if (downstreamEffect != null) {
                appendLine()
                appendLine("HOWLER:")
                append(downstreamEffect)
            }
        }
    }

    private fun NodeId.displayName(): String {
        return when (this) {
            NodeId.AUTH_NODE -> "AUTH"
            NodeId.TENANT_NODE -> "TENANT"
            NodeId.GOVERNANCE_NODE -> "GOVERNANCE"
            NodeId.NAVIGATION_NODE -> "NAVIGATION"
        }
    }

    private fun NodeState.symbol(): String {
        return when (this) {
            NodeState.OK -> "✓"
            NodeState.WARN -> "⚠"
            NodeState.STOP -> "⛔"
        }
    }
}
