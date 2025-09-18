package com.noyal.demo.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculateTotalPnlPercentageUseCaseTest {

    private lateinit var calculateTotalPnlPercentageUseCase: CalculateTotalPnlPercentageUseCase

    @Before
    fun setUp() {
        calculateTotalPnlPercentageUseCase = CalculateTotalPnlPercentageUseCase()
    }

    @Test
    fun `should calculate positive percentage when PnL is positive`() {
        val totalPnl = 500.0
        val totalInvestment = 1000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(50.0, result, 0.01)
    }

    @Test
    fun `should calculate negative percentage when PnL is negative`() {
        val totalPnl = -200.0
        val totalInvestment = 1000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(-20.0, result, 0.01)
    }

    @Test
    fun `should calculate zero percentage when PnL is zero`() {
        val totalPnl = 0.0
        val totalInvestment = 1000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should return zero when total investment is zero`() {
        val totalPnl = 500.0
        val totalInvestment = 0.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should handle decimal values with proper rounding`() {
        val totalPnl = 333.333
        val totalInvestment = 1000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(33.33, result, 0.01)
    }

    @Test
    fun `should calculate 100 percent gain correctly`() {
        val totalPnl = 1000.0
        val totalInvestment = 1000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(100.0, result, 0.01)
    }

    @Test
    fun `should calculate 100 percent loss correctly`() {
        val totalPnl = -1000.0
        val totalInvestment = 1000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(-100.0, result, 0.01)
    }

    @Test
    fun `should handle typical trading scenario with mixed results`() {
        val totalPnl = 1250.75
        val totalInvestment = 5000.0

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(25.02, result, 0.01)
    }

    @Test
    fun `should verify percentage calculation formula`() {
        val totalPnl = 750.0
        val totalInvestment = 2500.0
        val expectedPercentage = (totalPnl / totalInvestment) * 100

        val result = calculateTotalPnlPercentageUseCase.invoke(totalPnl, totalInvestment)

        assertEquals(expectedPercentage, result, 0.01)
        assertEquals(30.0, result, 0.01)
    }
}