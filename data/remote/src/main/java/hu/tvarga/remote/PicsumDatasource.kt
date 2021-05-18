package hu.tvarga.remote

import javax.inject.Inject

class PicsumDatasource @Inject constructor(private val picsumService: PicsumService) {

    fun fetchList(page: Int) = picsumService.fetchList(page)
    fun fetchItem(id: String) = picsumService.fetchItem(id)
}
