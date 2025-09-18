package com.noyal.demo.ui.portfolio

import com.noyal.demo.domain.model.PortfolioSummary
import com.noyal.demo.ui.portfolio.model.HoldingUiModel

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val userHoldings: List<HoldingUiModel> = emptyList(),
    val portfolioSummary: PortfolioSummary? = null,
    val error: String? = null
)
