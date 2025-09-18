package com.noyal.demo.ui.portfolio

import com.noyal.demo.ui.portfolio.model.HoldingUiModel
import com.noyal.demo.ui.portfolio.model.PortfolioSummary

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val userHoldings: List<HoldingUiModel> = emptyList(),
    val portfolioSummary: PortfolioSummary? = null,
    val error: String? = null
)
