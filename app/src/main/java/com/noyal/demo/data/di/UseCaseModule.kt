package com.noyal.demo.data.di

import com.noyal.demo.domain.usecase.CalculateCurrentValueUseCase
import com.noyal.demo.domain.usecase.CalculateHoldingPnlUseCase
import com.noyal.demo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.noyal.demo.domain.usecase.CalculateTodayPnlUseCase
import com.noyal.demo.domain.usecase.CalculateTotalInvestmentUseCase
import com.noyal.demo.domain.usecase.CalculateTotalPnlPercentageUseCase
import com.noyal.demo.domain.usecase.CalculateTotalPnlUseCase
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

    @Provides
    fun provideCalculateCurrentValueUseCase(): CalculateCurrentValueUseCase {
        return CalculateCurrentValueUseCase()
    }

    @Provides
    fun provideCalculateTotalInvestmentUseCase(): CalculateTotalInvestmentUseCase {
        return CalculateTotalInvestmentUseCase()
    }

    @Provides
    fun provideCalculateTodayPnlUseCase(): CalculateTodayPnlUseCase {
        return CalculateTodayPnlUseCase()
    }

    @Provides
    fun provideCalculateTotalPnlPercentageUseCase(): CalculateTotalPnlPercentageUseCase {
        return CalculateTotalPnlPercentageUseCase()
    }

    @Provides
    fun provideCalculateTotalPnlUseCase(
        calculateCurrentValueUseCase: CalculateCurrentValueUseCase,
        calculateTotalInvestmentUseCase: CalculateTotalInvestmentUseCase
    ): CalculateTotalPnlUseCase {
        return CalculateTotalPnlUseCase(
            calculateCurrentValueUseCase = calculateCurrentValueUseCase,
            calculateTotalInvestmentUseCase = calculateTotalInvestmentUseCase
        )
    }

    @Provides
    fun provideCalculatePortfolioSummaryUseCase(
        calculateCurrentValueUseCase: CalculateCurrentValueUseCase,
        calculateTotalInvestmentUseCase: CalculateTotalInvestmentUseCase,
        calculateTotalPnlUseCase: CalculateTotalPnlUseCase,
        calculateTodayPnlUseCase: CalculateTodayPnlUseCase,
        calculateTotalPnlPercentageUseCase: CalculateTotalPnlPercentageUseCase
    ): CalculatePortfolioSummaryUseCase {
        return CalculatePortfolioSummaryUseCase(
            calculateCurrentValueUseCase = calculateCurrentValueUseCase,
            calculateTotalInvestmentUseCase = calculateTotalInvestmentUseCase,
            calculateTotalPnlUseCase = calculateTotalPnlUseCase,
            calculateTodayPnlUseCase = calculateTodayPnlUseCase,
            calculateTotalPnlPercentageUseCase = calculateTotalPnlPercentageUseCase
        )
    }

}
