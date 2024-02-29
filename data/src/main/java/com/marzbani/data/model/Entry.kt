package com.marzbani.data.model

data class Entry(
    val id: String,
    val label: String,
    val children: List<Entry>?
)
