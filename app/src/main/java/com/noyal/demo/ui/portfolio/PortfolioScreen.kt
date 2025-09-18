package com.noyal.demo.ui.portfolio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.noyal.demo.ui.component.ErrorPlaceHolder
import com.noyal.demo.ui.portfolio.component.Holding
import com.noyal.demo.ui.portfolio.component.SummaryBar
import com.noyal.demo.ui.portfolio.model.HoldingUiModel

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PortfolioContent(
        uiState = uiState,
        onToggleSummary = viewModel::toggleSummary,
        onRetry = viewModel::retry
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PortfolioContent(
    modifier: Modifier = Modifier,
    uiState: PortfolioUiState,
    onToggleSummary: () -> Unit = {},
    onRetry: () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(topAppBarScrollBehavior = scrollBehavior) },
        bottomBar = {
            AnimatedVisibility(
                visible = uiState.portfolioSummary != null,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                uiState.portfolioSummary?.let { summary ->
                    SummaryBar(
                        currentValue = summary.currentValue,
                        totalInvestment = summary.totalInvestment,
                        todayProfitAndLoss = summary.todayPnl,
                        totalProfitAndLoss = summary.totalPnl,
                        totalProfitAndLossPercentage = summary.totalPnlPercentage,
                        isExpanded = uiState.isSummaryExpanded,
                        onExpand = { onToggleSummary() }
                    )
                }
            }
        },
    ) { innerPadding ->
        Crossfade(
            modifier = Modifier.padding(innerPadding),
            targetState = uiState.isLoading || uiState.error != null
        ) { isBusy ->
            if (isBusy) when {
                uiState.isLoading -> Loading()
                uiState.error != null -> ErrorPlaceHolder { onRetry() }
            } else HoldingList(holdings = uiState.userHoldings)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    onProfileClick: () -> Unit = {},
    onSortClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},

    ) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = topAppBarScrollBehavior,
        title = {
            Text("")
        },
        navigationIcon = {
            IconButton(onClick = { onProfileClick() }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile"
                )
            }
        }, actions = {
            IconButton(onClick = { onSortClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Sort,
                    contentDescription = "Profile"
                )
            }
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Profile"
                )
            }
        })
}