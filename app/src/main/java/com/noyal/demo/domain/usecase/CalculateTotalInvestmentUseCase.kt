package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding

class CalculateTotalInvestmentUseCase {
    
    operator fun invoke(holdings: List<UserHolding>): Double {
        return holdings.sumOf { holding ->
            holding.avgPrice * holding.quantity
        }
    }
}