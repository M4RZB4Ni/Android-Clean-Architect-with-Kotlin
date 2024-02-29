package com.marzbani.domain.entity

data class WorkspaceEntity(
    val name: String,
    val entries: List<EntryEntity>
)