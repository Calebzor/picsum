package hu.tvarga.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import hu.tvarga.model.PicsumItemEntity

@Dao
abstract class PicsumDao : BaseDao<PicsumItemEntity>() {

    @Query("SELECT * FROM picsums")
    abstract fun getPicsumItemEntitys(): PagingSource<Int, PicsumItemEntity>

    @Query("SELECT * FROM picsums WHERE id = :id LIMIT 1")
    abstract suspend fun getPicsumItemEntity(id: String): PicsumItemEntity

    @Query("DELETE FROM picsums")
    abstract suspend fun clearRepos()

    suspend fun save(picsumItemEntitys: List<PicsumItemEntity>) {
        insert(picsumItemEntitys)
    }
}
