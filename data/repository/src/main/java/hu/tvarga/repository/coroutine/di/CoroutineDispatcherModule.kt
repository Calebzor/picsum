package hu.tvarga.repository.coroutine.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.tvarga.repository.coroutine.AppDispatchers
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatcherModule {

    @Provides
    fun dispatchers(): AppDispatchers {
        return AppDispatchers(main = Dispatchers.Main, io = Dispatchers.IO)
    }
}
