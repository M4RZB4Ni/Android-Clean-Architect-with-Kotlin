package com.marzbani.data.mapper

import com.google.gson.Gson
import com.marzbani.data.model.DetailsModel
import com.marzbani.domain.entity.DetailsEntity

class DetailsEntityMapper {


    private val gson = Gson()

    fun fromEntity(entity: DetailsEntity): DetailsModel {
        val json = gson.toJson(entity)
        return gson.fromJson(json, DetailsModel::class.java)
    }

    fun toEntity(model: DetailsModel): DetailsEntity {
        val json = gson.toJson(model)
        return gson.fromJson(json, DetailsEntity::class.java)
    }
}
