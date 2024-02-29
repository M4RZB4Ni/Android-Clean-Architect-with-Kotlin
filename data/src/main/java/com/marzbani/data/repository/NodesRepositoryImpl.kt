package com.marzbani.data.repository

import com.marzbani.data.mapper.DetailsEntityMapper
import com.marzbani.data.mapper.TreeNodeEntityMapper
import com.marzbani.data.source.NodesService
import com.marzbani.domain.entity.DetailsEntity
import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.repository.NodesRepository
import io.reactivex.Single

class NodesRepositoryImpl(private val service: NodesService,private val nodeMap: TreeNodeEntityMapper,private val detailsEntityMapper: DetailsEntityMapper):NodesRepository {
    override fun getNodes(url: String): Single<List<TreeNodeEntity>> {
        return service.getNodes(url)
            .map { nodeMap.mapFromTreeNodeList(it) }
    }
    override fun getAdditionalData(dataCode: String): Single<DetailsEntity> {
        return service.getAdditionalData(dataCode)
            .map { detailsEntityMapper.mapFromData(it) }
    }


}