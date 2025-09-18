package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding

class CalculateTotalPnlUseCase(
    private val calculateCurrentValueUseCase: CalculateCurrentValueUseCase,
    private val calculateTotalInvestmentUseCase: CalculateTotalInvestmentUseCase
) {
    
    operator fun invoke(holdings: List<UserHolding>): Double {
        val currentValue = calculateCurrentValueUseCase(holdings)
        val totalInvestment = calculateTotalInvestmentUseCase(holdings)
        return currentValue - totalInvestment
    }
}