package com.marzbani.domain.mapper

import com.marzbani.data.model.Entry
import com.marzbani.data.model.TreeNodeModel
import com.marzbani.data.model.Workspace
import com.marzbani.domain.entity.EntryEntity
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.entity.WorkspaceEntity

// Domain Layer
class TreeNodeEntityMapper {
    fun mapFromTreeNode(treeNode: TreeNodeModel): TreeNodeEntity {
        return TreeNodeEntity(
            label = treeNode.label,
            entries = treeNode.entries?.map { mapEntry(it) } ?: emptyList(),
            children = treeNode.children?.map { mapFromTreeNode(it) } ?: emptyList(),
            workspace = treeNode.workspace?.let { mapWorkspace(it) }
        )
    }

    private fun mapWorkspace(node: Workspace): WorkspaceEntity {
        return WorkspaceEntity(
            name = node.label,
            entries = node.children?.map { mapEntry(it) } ?: emptyList()
        )
    }

    private fun mapEntry(node: Entry): EntryEntity {
        return EntryEntity(
            id = node.id,
            label = node.label,
            subEntries = node.children?.map { mapEntry(it) } ?: emptyList()
        )
    }

}
