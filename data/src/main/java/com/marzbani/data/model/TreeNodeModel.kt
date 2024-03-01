package com.marzbani.data.model

import com.google.gson.annotations.SerializedName

data class TreeNodeModel(
    val label: String,
    val id: String?,
    @SerializedName("children")
    val children: List<TreeNodeModel>?
)