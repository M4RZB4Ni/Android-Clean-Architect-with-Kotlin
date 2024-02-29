package com.marzbani.domain.usecase

import com.marzbani.domain.entity.DetailsEntity
import com.marzbani.domain.repository.NodesRepository
import com.marzbani.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repository: NodesRepository):SingleUseCase<String,DetailsEntity>() {
    override fun buildUseCaseSingle(params: String): Single<DetailsEntity> {
        return  repository.getAdditionalData(params)
    }

}