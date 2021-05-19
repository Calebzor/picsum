package hu.tvarga.listapi

import androidx.lifecycle.LiveData
import hu.tvarga.core.resource.Resource
import hu.tvarga.model.PicsumItem

interface PicsumRepository {
    suspend fun getPicsums(forceRefresh: Boolean = false, page: Int = 1): LiveData<Resource<List<PicsumItem>>>
    suspend fun getPicsum(id: String): LiveData<PicsumItem>
}
