package hu.tvarga.repository

import androidx.lifecycle.LiveData
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.model.PicsumItemEntity
import hu.tvarga.repository.resource.Resource

class PicsumRepositoryImpl : PicsumRepository {

    override suspend fun getPicsums(forceRefresh: Boolean): LiveData<Resource<List<PicsumItemEntity>>> {
    }

    override suspend fun getPicsum(id: String): LiveData<PicsumItemEntity> {
    }
}
