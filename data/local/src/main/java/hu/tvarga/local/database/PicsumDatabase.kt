package hu.tvarga.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.tvarga.local.dao.PicsumDao
import hu.tvarga.model.PicsumItemEntity

@Database(entities = [PicsumItemEntity::class], version = 1, exportSchema = false)
abstract class PicsumDatabase : RoomDatabase() {

    // DAO
    abstract fun picsumDao(): PicsumDao

    companion object {

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, PicsumDatabase::class.java, "PicsumApp.db").build()
    }
}
