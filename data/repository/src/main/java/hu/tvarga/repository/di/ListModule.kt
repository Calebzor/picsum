package hu.tvarga.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.local.database.PicsumDatabase
import hu.tvarga.remote.PicsumDatasource
import hu.tvarga.repository.PicsumRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    fun listRepository(datasource: PicsumDatasource, database: PicsumDatabase): PicsumRepository {
        return PicsumRepositoryImpl(datasource, database)
    }
}
