package com.noyal.demo.ui.portfolio.model

import com.noyal.demo.domain.model.UserHolding
import com.noyal.demo.domain.usecase.CalculateHoldingPnlUseCase

data class HoldingUiModel(
    val holding: UserHolding,
    val profitAndLoss: Double
)

fun UserHolding.toUiModel(calculatePnl: CalculateHoldingPnlUseCase): HoldingUiModel =
    HoldingUiModel(
        holding = this,
        profitAndLoss = calculatePnl(this)
    )
