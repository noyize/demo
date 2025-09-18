package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding

class CalculateTodayPnlUseCase {
    
    operator fun invoke(holdings: List<UserHolding>): Double {
        return holdings.sumOf { holding ->
            (holding.close - holding.ltp) * holding.quantity
        }
    }
}