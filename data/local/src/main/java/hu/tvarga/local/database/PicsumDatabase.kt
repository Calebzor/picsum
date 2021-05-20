package hu.tvarga.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.tvarga.local.dao.PicsumDao
import hu.tvarga.local.dao.RemoteKeysDao
import hu.tvarga.model.PicsumItemEntity
import hu.tvarga.model.RemoteKeys

@Database(entities = [PicsumItemEntity::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class PicsumDatabase : RoomDatabase() {

    abstract fun picsumDao(): PicsumDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, PicsumDatabase::class.java, "PicsumApp.db").build()
    }
}
