package com.noyal.demo.ui.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noyal.demo.core.Result
import com.noyal.demo.domain.repository.RemoteRepository
import com.noyal.demo.domain.usecase.CalculateHoldingPnlUseCase
import com.noyal.demo.domain.usecase.CalculatePortfolioSummaryUseCase
import com.noyal.demo.ui.portfolio.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val calculateHoldingPnlUseCase: CalculateHoldingPnlUseCase,
    private val calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserHoldings()
    }

    fun retry() {
        getUserHoldings()
    }

    fun toggleSummary() {
        _uiState.update { it.copy(isSummaryExpanded = !it.isSummaryExpanded) }
    }

    private var fetchHoldingsJob: Job? = null
    private fun getUserHoldings() {
        fetchHoldingsJob?.cancel()
        fetchHoldingsJob = viewModelScope.launch {
            remoteRepository.getHoldings().collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                userHoldings = result.data.map { holding ->
                                    holding.toUiModel(calculateHoldingPnlUseCase)
                                },
                                portfolioSummary = calculatePortfolioSummaryUseCase(result.data)
                            )
                        }
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                portfolioSummary = null,
                                error = "Something went wrong, please try again."
                            )
                        }
                    }
                }
            }
        }
    }
}