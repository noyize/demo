package com.noyal.demo.ui.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noyal.demo.core.Result
import com.noyal.demo.domain.repository.RemoteRepository
import com.noyal.demo.domain.usecase.CalculateHoldingPnlUseCase
import com.noyal.demo.ui.portfolio.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val calculateHoldingPnlUseCase: CalculateHoldingPnlUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserHoldings()
    }

    private fun getUserHoldings() {
        viewModelScope.launch {
            remoteRepository.getHoldings().collectLatest { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                userHoldings = result.data.map { holding ->
                                    holding.toUiModel(calculateHoldingPnlUseCase)
                                }
                            )
                        }
                    }

                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = "Something went wrong, please try again."
                            )
                        }
                    }
                }
            }
        }
    }
}