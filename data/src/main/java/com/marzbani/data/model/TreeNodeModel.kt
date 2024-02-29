package com.marzbani.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class TreeNodeModel(
    val label: String,
    val children: List<TreeNodeModel>?,
    val entries: List<Entry>?,
    val workspace: Workspace?
) {
    companion object {
        fun fromJson(json: String): List<TreeNodeModel> {
            val listType = object : TypeToken<List<TreeNodeModel>>() {}.type
            return Gson().fromJson(json, listType)
        }
    }
}