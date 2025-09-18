package com.noyal.demo.ui.portfolio

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.noyal.demo.domain.model.UserHolding
import com.noyal.demo.ui.portfolio.component.Holding
import com.noyal.demo.ui.portfolio.model.HoldingUiModel

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PortfolioContent(uiState = uiState)

}

@Composable
private fun PortfolioContent(
    modifier: Modifier = Modifier,
    uiState: PortfolioUiState
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Crossfade(
            modifier = Modifier.padding(innerPadding),
            targetState = uiState.isLoading || uiState.error != null
        ) { isBusy ->
            if (isBusy) {
                when {
                    uiState.isLoading -> Loading()
                    uiState.error != null -> {}
                }
            } else {
                HoldingList(holdings = uiState.userHoldings)
            }
        }

    }
}

@Composable
private fun HoldingList(modifier: Modifier = Modifier, holdings: List<HoldingUiModel>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 40.dp)
    ) {
        items(items = holdings, key = { it.holding.symbol }) { model ->
            Holding(
                symbol = model.holding.symbol,
                ltp = model.holding.ltp,
                quantity = model.holding.quantity,
                profitAndLose = model.profitAndLoss
            )
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth(0.3f))
    }
}