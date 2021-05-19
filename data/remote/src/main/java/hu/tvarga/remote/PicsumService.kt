package hu.tvarga.remote

import hu.tvarga.model.PicsumApiObject
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsumService {

    //    https://picsum.photos/v2/list?page=2&limit=100
    @GET("list")
    fun fetchList(
        @Query("page") page: Int = 1, @Query("limit") limit: Int = 30
    ): Deferred<List<PicsumApiObject>>

}