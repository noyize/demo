package com.noyal.demo.ui.portfolio

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.noyal.demo.domain.model.UserHolding

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
private fun HoldingList(modifier: Modifier = Modifier, holdings: List<UserHolding>) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(holdings) { holding ->
            Text(holding.symbol)
        }
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth(0.3f))
    }
}