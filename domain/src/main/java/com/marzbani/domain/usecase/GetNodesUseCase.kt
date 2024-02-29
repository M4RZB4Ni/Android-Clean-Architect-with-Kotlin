package com.marzbani.domain.usecase

import com.marzbani.domain.entity.TreeNodeEntity
import com.marzbani.domain.repository.NodesRepository
import com.marzbani.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetNodesUseCase @Inject constructor(private val repository: NodesRepository):
    SingleUseCase<String,List<TreeNodeEntity>>() {
    override fun buildUseCaseSingle(params: String): Single<List<TreeNodeEntity>> {
        return repository.getNodes(params)
    }


}