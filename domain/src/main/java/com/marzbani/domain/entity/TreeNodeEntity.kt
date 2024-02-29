package com.marzbani.domain.entity

data class TreeNodeEntity(
    val label: String,
    val entries: List<EntryEntity>?,
    val children: List<TreeNodeEntity>?,
    val workspace: WorkspaceEntity?
)

fun removeNode(nodes: List<TreeNodeEntity>, selectedNode: TreeNodeEntity): List<TreeNodeEntity> {
    return nodes.flatMap { node ->
        if (node == selectedNode) {
            emptyList() // Exclude the selected node and its children
        } else {
            val updatedChildren = removeNode(node.children.orEmpty(), selectedNode)
            listOf(node.copy(children = updatedChildren))
        }
    }
}
fun moveNode(nodes: List<TreeNodeEntity>, movedNode: TreeNodeEntity): List<TreeNodeEntity> {
    return nodes.map { node ->
        if (node == movedNode) {
            val newIndex = nodes.indexOf(node) - 1
            val parentNode = nodes.getOrNull(newIndex)
            val updatedParent = parentNode?.copy(
                children = (parentNode.children.orEmpty() + node).distinct()
            )
            updatedParent ?: node
        } else {
            node.copy(children = moveNode(node.children.orEmpty(), movedNode))
        }
    }
}


