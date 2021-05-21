package hu.tvarga.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.local.database.PicsumDatabase
import hu.tvarga.model.entity.PicsumItemEntity
import hu.tvarga.remote.PicsumDatasource
import kotlinx.coroutines.flow.Flow

class PicsumRepositoryImpl(
    private val datasource: PicsumDatasource,
    private val database: PicsumDatabase
) : PicsumRepository {

    override fun getPicsums(): Flow<PagingData<PicsumItemEntity>> {

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PicsumRemoteMediator(
                datasource,
                database
            )
        ) {
            database.picsumDao().getPicsumItemEntities()
        }.flow
    }

    override fun getPicsum(id: String): Flow<PicsumItemEntity> {
        return database.picsumDao().getPicsumItemEntity(id)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }

}


