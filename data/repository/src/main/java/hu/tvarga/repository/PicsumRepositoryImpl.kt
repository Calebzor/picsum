package hu.tvarga.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.tvarga.core.resource.Resource
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.local.dao.PicsumDao
import hu.tvarga.model.PicsumApiObject
import hu.tvarga.model.PicsumItem
import hu.tvarga.model.PicsumItemEntity
import hu.tvarga.remote.PicsumDatasource
import hu.tvarga.repository.resource.NetworkBoundResource
import kotlinx.coroutines.Deferred

class PicsumRepositoryImpl(
    private val datasource: PicsumDatasource,
    private val dao: PicsumDao
) : PicsumRepository {

    override suspend fun getPicsums(forceRefresh: Boolean, page: Int): LiveData<Resource<List<PicsumItem>>> {
        return object : NetworkBoundResource<List<PicsumItem>, List<PicsumApiObject>>() {

            override fun processResponse(response: List<PicsumApiObject>): List<PicsumItem> =
                response.map { it.toPicsum() }

            override suspend fun saveCallResults(items: List<PicsumItem>) =
                dao.save(items.map { it.toPicsumItemEntity() })

            override fun shouldFetch(data: List<PicsumItem>?): Boolean = data == null || data.isEmpty() || forceRefresh

            override suspend fun loadFromDb(): List<PicsumItem> = dao.getPicsumItemEntitys().map { it.toPicsumItem() }

            override fun createCallAsync(): Deferred<List<PicsumApiObject>> = datasource.fetchList(page)

        }.build().asLiveData()
    }

    override suspend fun getPicsum(id: String): LiveData<PicsumItem> {
        val picsumEntity = dao.getPicsumItemEntity(id)
        val picsumLiveData = MutableLiveData<PicsumItem>()
        picsumEntity.let { picsumLiveData.postValue(it.toPicsumItem()) }
        return picsumLiveData
    }
}

private fun PicsumApiObject.toPicsum(): PicsumItem =
    PicsumItem(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        downloadUrl = this.downloadUrl,
    )

private fun PicsumItem.toPicsumItemEntity(): PicsumItemEntity =
    PicsumItemEntity(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        downloadUrl = this.downloadUrl,
    )

private fun PicsumItemEntity.toPicsumItem(): PicsumItem =
    PicsumItem(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        downloadUrl = this.downloadUrl,
    )
