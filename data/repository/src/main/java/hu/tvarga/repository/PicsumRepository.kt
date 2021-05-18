package hu.tvarga.listapi

import androidx.lifecycle.LiveData
import hu.tvarga.model.PicsumItemEntity
import hu.tvarga.repository.resource.Resource

interface PicsumRepository {
    suspend fun getPicsums(forceRefresh: Boolean = false): LiveData<Resource<List<PicsumItemEntity>>>
    suspend fun getPicsum(id: String): LiveData<PicsumItemEntity>
}
