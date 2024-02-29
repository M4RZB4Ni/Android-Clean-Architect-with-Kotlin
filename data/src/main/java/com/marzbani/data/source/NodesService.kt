package com.marzbani.data.source

import com.marzbani.data.model.DetailsModel
import com.marzbani.data.model.TreeNodeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface NodesService {
    @GET
    fun getNodes(@Url url: String): Single<TreeNodeModel>
    @GET("entries/{dataCode}.json")
    fun getAdditionalData(@Path("dataCode") imageCode: String): Single<DetailsModel>

}