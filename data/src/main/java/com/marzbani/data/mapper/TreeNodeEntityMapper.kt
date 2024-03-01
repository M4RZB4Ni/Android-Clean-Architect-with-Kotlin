package com.marzbani.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marzbani.data.model.TreeNodeModel
import com.marzbani.domain.entity.TreeNodeEntity


class TreeNodeEntityMapper {
    private val gson = Gson()


    fun toEntity(model: TreeNodeModel): TreeNodeEntity {
        val json = gson.toJson(model)
        return gson.fromJson(json, TreeNodeEntity::class.java)
    }

    fun toEntityList(models: List<TreeNodeModel>): List<TreeNodeEntity> {
        val json = gson.toJson(models)
        val listType = object : TypeToken<List<TreeNodeEntity>>() {}.type
        return gson.fromJson(json, listType)
    }

}
