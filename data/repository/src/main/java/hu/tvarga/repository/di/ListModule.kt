package hu.tvarga.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tvarga.listapi.PicsumRepository
import hu.tvarga.local.dao.PicsumDao
import hu.tvarga.remote.PicsumDatasource
import hu.tvarga.repository.PicsumRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    fun listRepository(datasource: PicsumDatasource, dao: PicsumDao): PicsumRepository {
        return PicsumRepositoryImpl(datasource, dao)
    }
}
