package hu.tvarga.remote

import javax.inject.Inject

class PicsumDatasource @Inject constructor(private val picsumService: PicsumService) {

    suspend fun fetchList(page: Int, limit: Int) = picsumService.fetchList(page, limit)
}
