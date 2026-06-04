package io.nodaiity.ohbuoy.core

data class NodePacket(
    val traceId: String,
    val nodeId: NodeId,
    val state: NodeState,
    val message: String,
    val timestamp: Long,
    val upstreamNode: NodeId? = null,
    val downstreamEffect: String? = null
)
