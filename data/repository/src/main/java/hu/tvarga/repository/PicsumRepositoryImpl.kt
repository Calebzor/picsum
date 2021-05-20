package hu.tvarga.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.local.database.PicsumDatabase
import hu.tvarga.model.PicsumItemEntity
import hu.tvarga.remote.PicsumDatasource
import kotlinx.coroutines.flow.Flow

class PicsumRepositoryImpl(
    private val datasource: PicsumDatasource,
    private val database: PicsumDatabase
) : PicsumRepository {

    override fun getPicsums(): Flow<PagingData<PicsumItemEntity>> {

        val pagingSourceFactory = { database.picsumDao().getPicsumItemEntitys() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PicsumRemoteMediator(
                datasource,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }

}


