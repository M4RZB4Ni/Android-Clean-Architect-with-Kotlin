package com.marzbani.domain.mapper

import com.marzbani.data.model.DetailsModel
import com.marzbani.domain.entity.DetailsEntity

class DetailsEntityMapper {
    fun mapFromData(data: DetailsModel): DetailsEntity {
        return DetailsEntity(
            id = data.id,
            createdAt = data.createdAt,
            createdBy = data.createdBy,
            lastModifiedAt = data.lastModifiedAt,
            lastModifiedBy = data.lastModifiedBy,
            description = data.description
        )
    }
}
