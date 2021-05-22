package hu.tvarga.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tvarga.remote.PicsumService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    fun picsumService(retrofit: Retrofit): PicsumService {
        return retrofit.create(PicsumService::class.java)
    }

}

