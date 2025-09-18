package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.PortfolioSummary
import com.noyal.demo.domain.model.UserHolding

class CalculatePortfolioSummaryUseCase(
    private val calculateCurrentValueUseCase: CalculateCurrentValueUseCase,
    private val calculateTotalInvestmentUseCase: CalculateTotalInvestmentUseCase,
    private val calculateTotalPnlUseCase: CalculateTotalPnlUseCase,
    private val calculateTodayPnlUseCase: CalculateTodayPnlUseCase,
    private val calculateTotalPnlPercentageUseCase: CalculateTotalPnlPercentageUseCase
) {

    operator fun invoke(holdings: List<UserHolding>): PortfolioSummary {
        val currentValue = calculateCurrentValueUseCase(holdings)
        val totalInvestment = calculateTotalInvestmentUseCase(holdings)
        val totalPnl = calculateTotalPnlUseCase(holdings)
        val todayPnl = calculateTodayPnlUseCase(holdings)
        val totalPnlPercentage = calculateTotalPnlPercentageUseCase(totalPnl, totalInvestment)

        return PortfolioSummary(
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            totalPnl = totalPnl,
            todayPnl = todayPnl,
            totalPnlPercentage = totalPnlPercentage
        )
    }
}