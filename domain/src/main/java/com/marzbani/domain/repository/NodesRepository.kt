package com.marzbani.domain.repository

import com.marzbani.domain.entity.TreeNodeEntity
import io.reactivex.Single

interface NodesRepository {

    fun getNodes(): Single<TreeNodeEntity>



}