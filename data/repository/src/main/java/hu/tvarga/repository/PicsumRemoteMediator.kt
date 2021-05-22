package hu.tvarga.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import hu.tvarga.local.database.PicsumDatabase
import hu.tvarga.model.entity.PicsumItemEntity
import hu.tvarga.model.entity.RemoteKeysEntity
import hu.tvarga.model.toPicsumItemEntity
import hu.tvarga.remote.PicsumDatasource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PicsumRemoteMediator @Inject constructor(
    private val datasource: PicsumDatasource,
    private val database: PicsumDatabase
) : RemoteMediator<Int, PicsumItemEntity>() {

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PicsumItemEntity>): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> STARTING_PAGE_INDEX
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey =
                        remoteKeys?.nextKey
                            ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            val apiResponse = datasource.fetchList(page, state.config.pageSize)

            val picsums: List<PicsumItemEntity> = apiResponse.map { it.toPicsumItemEntity() }
            val endOfPaginationReached = picsums.isEmpty()
            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.picsumDao().clear()
                    database.remoteKeysDao().clearRemoteKeys()
                }
                val nextKey = page + 1
                val keys = picsums.map {
                    RemoteKeysEntity(id = it.id, nextKey = nextKey)
                }
                database.remoteKeysDao().insert(keys)
                database.picsumDao().insert(picsums)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PicsumItemEntity>): RemoteKeysEntity? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { picsum ->
                // Get the remote keys of the last item retrieved
                database.remoteKeysDao().remoteKeysPicsumId(picsum.id)
            }
    }

}



