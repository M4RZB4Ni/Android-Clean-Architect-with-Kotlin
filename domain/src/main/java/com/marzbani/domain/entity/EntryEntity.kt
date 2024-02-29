package com.marzbani.domain.entity

data class EntryEntity(
    val id: String,
    val label: String,
    val subEntries: List<EntryEntity>?
)
