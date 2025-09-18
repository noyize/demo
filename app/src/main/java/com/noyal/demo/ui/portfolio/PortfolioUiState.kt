package com.noyal.demo.ui.portfolio

import com.noyal.demo.domain.model.UserHolding

data class PortfolioUiState(
    val isLoading: Boolean = false,
    val userHoldings: List<UserHolding> = emptyList(),
    val error: String? = null
)
