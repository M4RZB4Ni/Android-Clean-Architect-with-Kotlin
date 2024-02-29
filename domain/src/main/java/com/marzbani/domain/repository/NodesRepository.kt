package com.marzbani.domain.repository

import com.marzbani.domain.entity.DetailsEntity
import com.marzbani.domain.entity.TreeNodeEntity
import io.reactivex.Single

interface NodesRepository {
    fun getNodes(url:String): Single<TreeNodeEntity>
    fun getAdditionalData(dataCode:String):Single<DetailsEntity>
}