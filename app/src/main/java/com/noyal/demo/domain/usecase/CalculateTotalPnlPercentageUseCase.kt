package com.noyal.demo.domain.usecase

import com.noyal.demo.core.roundToDecimalPlaces

class CalculateTotalPnlPercentageUseCase {
    operator fun invoke(totalPnl: Double, totalInvestment: Double): Double {
        return if (totalInvestment > 0) {
            (((totalPnl / totalInvestment) * 100)).roundToDecimalPlaces(2)
        } else {
            0.0
        }
    }
}