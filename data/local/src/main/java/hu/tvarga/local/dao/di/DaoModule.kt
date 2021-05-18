package hu.tvarga.local.dao.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.tvarga.local.dao.PicsumDao
import hu.tvarga.local.database.PicsumDatabase

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun database(@ApplicationContext context: Context): PicsumDatabase {
        return PicsumDatabase.buildDatabase(context)
    }

    @Provides
    fun picsumDao(picsumDatabase: PicsumDatabase): PicsumDao {
        return picsumDatabase.picsumDao()
    }

}
