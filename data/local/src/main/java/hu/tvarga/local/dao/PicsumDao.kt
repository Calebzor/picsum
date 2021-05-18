package hu.tvarga.local.dao

import androidx.room.Dao
import androidx.room.Query
import hu.tvarga.model.PicsumItemEntity

@Dao
abstract class PicsumDao : BaseDao<PicsumItemEntity>() {

    @Query("SELECT * FROM PicsumItemEntity LIMIT 30")
    abstract suspend fun getPicsumItemEntitys(): List<PicsumItemEntity>

    @Query("SELECT * FROM PicsumItemEntity WHERE id = :id LIMIT 1")
    abstract suspend fun getPicsumItemEntity(id: String): PicsumItemEntity

    suspend fun save(picsumItemEntity: PicsumItemEntity) {
        insert(picsumItemEntity)
    }

    suspend fun save(picsumItemEntitys: List<PicsumItemEntity>) {
        insert(picsumItemEntitys)
    }
}
