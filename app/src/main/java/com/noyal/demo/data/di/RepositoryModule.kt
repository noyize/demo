package com.noyal.demo.data.di

import com.noyal.demo.data.repository.RemoteRepositoryImp
import com.noyal.demo.domain.repository.RemoteRepository
import com.noyal.demo.data.remote.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(
        apiService: RemoteApiService
    ): RemoteRepository {
        return RemoteRepositoryImp(apiService)
    }
}
