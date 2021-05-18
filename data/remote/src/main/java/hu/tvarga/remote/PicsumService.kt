package hu.tvarga.remote

import hu.tvarga.model.PicsumItemEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumService {

    //    https://picsum.photos/v2/list?page=2&limit=100
    @GET("v2/list?page={page}")
    fun fetchList(@Path("page ") page: Int): Deferred<List<PicsumItemEntity>>

    //    https://picsum.photos/id/0/info
    @GET("id/{id}/info")
    fun fetchItem(@Path("id") id: String): Deferred<PicsumItemEntity>
}
