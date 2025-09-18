package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding

class CalculateCurrentValueUseCase {
    
    operator fun invoke(holdings: List<UserHolding>): Double {
        return holdings.sumOf { holding ->
            holding.ltp * holding.quantity
        }
    }
}
