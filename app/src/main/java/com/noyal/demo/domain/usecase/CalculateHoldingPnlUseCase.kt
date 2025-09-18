package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding

class CalculateHoldingPnlUseCase {
    operator fun invoke(holding: UserHolding): Double {
        return (holding.ltp - holding.avgPrice) * holding.quantity
    }
}
