package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculateTotalPnlUseCaseTest {

    @Mock
    private lateinit var calculateCurrentValueUseCase: CalculateCurrentValueUseCase

    @Mock
    private lateinit var calculateTotalInvestmentUseCase: CalculateTotalInvestmentUseCase

    private lateinit var useCase: CalculateTotalPnlUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CalculateTotalPnlUseCase(
            calculateCurrentValueUseCase,
            calculateTotalInvestmentUseCase
        )
    }

    @Test
    fun `should return correct PnL when current value is greater than investment`() {
        val holdings = listOf(
            UserHolding("TEST", 10, 100.0, 50.0, 95.0)
        )
        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(1000.0)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(500.0)

        val result = useCase(holdings)

        assertEquals(500.0, result, 0.01)
        verify(calculateCurrentValueUseCase).invoke(holdings)
        verify(calculateTotalInvestmentUseCase).invoke(holdings)
    }

    @Test
    fun `should return negative PnL when current value is less than investment`() {
        val holdings = listOf(
            UserHolding("TEST", 10, 40.0, 50.0, 45.0)
        )
        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(400.0)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(500.0)

        val result = useCase(holdings)

        assertEquals(-100.0, result, 0.01)
    }

    @Test
    fun `should return zero PnL when current value equals investment`() {
        val holdings = listOf(
            UserHolding("TEST", 10, 50.0, 50.0, 48.0)
        )
        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(500.0)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(500.0)

        val result = useCase(holdings)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should handle empty holdings list`() {
        val emptyHoldings = emptyList<UserHolding>()
        `when`(calculateCurrentValueUseCase(emptyHoldings)).thenReturn(0.0)
        `when`(calculateTotalInvestmentUseCase(emptyHoldings)).thenReturn(0.0)

        val result = useCase(emptyHoldings)

        assertEquals(0.0, result, 0.01)
    }
}