package com.noyal.demo.ui.portfolio

import com.noyal.demo.ui.portfolio.model.HoldingUiModel

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val userHoldings: List<HoldingUiModel> = emptyList(),
    val error: String? = null
)
