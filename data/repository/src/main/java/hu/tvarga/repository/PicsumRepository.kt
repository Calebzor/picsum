package hu.tvarga.listapi

import androidx.paging.PagingData
import hu.tvarga.model.PicsumItemEntity
import kotlinx.coroutines.flow.Flow

interface PicsumRepository {
    fun getPicsums(): Flow<PagingData<PicsumItemEntity>>
    fun getPicsum(id: String): Flow<PicsumItemEntity>
}
