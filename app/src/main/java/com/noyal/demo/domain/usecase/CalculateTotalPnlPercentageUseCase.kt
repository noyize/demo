package com.noyal.demo.domain.usecase

class CalculateTotalPnlPercentageUseCase {
    operator fun invoke(totalPnl: Double, totalInvestment: Double): Double {
        return if (totalInvestment > 0) {
            (totalPnl / totalInvestment) * 100
        } else {
            0.0
        }
    }
}