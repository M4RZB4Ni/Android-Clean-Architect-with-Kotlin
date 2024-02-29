package com.marzbani.data.model

data class Workspace(
    val label: String,
    val children: List<Entry>?
)