package hu.tvarga.local.dao

import androidx.room.Dao
import androidx.room.Query
import hu.tvarga.model.entity.RemoteKeysEntity

@Dao
abstract class RemoteKeysDao : BaseDao<RemoteKeysEntity>() {

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    abstract suspend fun remoteKeysPicsumId(id: String): RemoteKeysEntity?

    @Query("DELETE FROM remote_keys")
    abstract suspend fun clearRemoteKeys()
}
