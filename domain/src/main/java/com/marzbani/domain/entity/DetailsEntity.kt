package com.marzbani.domain.entity

data class DetailsEntity(
    val id: String,
    val createdAt: String,
    val createdBy: String,
    val lastModifiedAt: String,
    val lastModifiedBy: String,
    val description: String
)