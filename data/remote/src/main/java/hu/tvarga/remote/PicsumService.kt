package hu.tvarga.remote

import hu.tvarga.model.api.PicsumApiObject
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumService {

    @GET("list")
    suspend fun fetchList(
        @Query("page") page: Int, @Query("limit") limit: Int
    ): List<PicsumApiObject>

}
