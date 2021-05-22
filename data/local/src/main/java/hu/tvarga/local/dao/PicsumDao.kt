package hu.tvarga.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import hu.tvarga.model.entity.PicsumItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PicsumDao : BaseDao<PicsumItemEntity>() {

    @Query("SELECT * FROM picsums")
    abstract fun getPicsumItemEntities(): PagingSource<Int, PicsumItemEntity>

    @Query("SELECT * FROM picsums WHERE id = :id LIMIT 1")
    abstract fun getPicsumItemEntity(id: String): Flow<PicsumItemEntity>

    @Query("DELETE FROM picsums")
    abstract suspend fun clear()

}
