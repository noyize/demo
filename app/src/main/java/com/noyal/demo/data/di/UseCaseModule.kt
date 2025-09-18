package com.noyal.demo.data.di

import com.noyal.demo.domain.usecase.CalculateHoldingPnlUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCalculateHoldingPnlUseCase(): CalculateHoldingPnlUseCase {
        return CalculateHoldingPnlUseCase()
    }
}
