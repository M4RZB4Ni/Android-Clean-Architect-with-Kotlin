package com.marzbani.domain.entity

data class TreeNodeEntity(
    val label: String,
    val entries: List<EntryEntity>?,
    val children: List<TreeNodeEntity>?,
    val workspace: WorkspaceEntity?
)