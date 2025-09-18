package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CalculatePortfolioSummaryUseCaseTest {

    @Mock
    private lateinit var calculateCurrentValueUseCase: CalculateCurrentValueUseCase

    @Mock
    private lateinit var calculateTotalInvestmentUseCase: CalculateTotalInvestmentUseCase

    @Mock
    private lateinit var calculateTotalPnlUseCase: CalculateTotalPnlUseCase

    @Mock
    private lateinit var calculateTodayPnlUseCase: CalculateTodayPnlUseCase

    @Mock
    private lateinit var calculateTotalPnlPercentageUseCase: CalculateTotalPnlPercentageUseCase

    private lateinit var calculatePortfolioSummaryUseCase: CalculatePortfolioSummaryUseCase

    @Before
    fun setUp() {
        calculatePortfolioSummaryUseCase = CalculatePortfolioSummaryUseCase(
            calculateCurrentValueUseCase,
            calculateTotalInvestmentUseCase,
            calculateTotalPnlUseCase,
            calculateTodayPnlUseCase,
            calculateTotalPnlPercentageUseCase
        )
    }

    @Test
    fun `should return correct portfolio summary with positive values`() {
        val holdings = listOf(
            mock(UserHolding::class.java),
            mock(UserHolding::class.java)
        )
        val expectedCurrentValue = 15000.0
        val expectedTotalInvestment = 12000.0
        val expectedTotalPnl = 3000.0
        val expectedTodayPnl = 500.0
        val expectedTotalPnlPercentage = 25.0

        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(expectedCurrentValue)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(expectedTotalInvestment)
        `when`(calculateTotalPnlUseCase(holdings)).thenReturn(expectedTotalPnl)
        `when`(calculateTodayPnlUseCase(holdings)).thenReturn(expectedTodayPnl)
        `when`(
            calculateTotalPnlPercentageUseCase(
                expectedTotalPnl,
                expectedTotalInvestment
            )
        ).thenReturn(expectedTotalPnlPercentage)


        val result = calculatePortfolioSummaryUseCase(holdings)


        assertEquals(expectedCurrentValue, result.currentValue)
        assertEquals(expectedTotalInvestment, result.totalInvestment)
        assertEquals(expectedTotalPnl, result.totalPnl)
        assertEquals(expectedTodayPnl, result.todayPnl)
        assertEquals(expectedTotalPnlPercentage, result.totalPnlPercentage)

        verify(calculateCurrentValueUseCase).invoke(holdings)
        verify(calculateTotalInvestmentUseCase).invoke(holdings)
        verify(calculateTotalPnlUseCase).invoke(holdings)
        verify(calculateTodayPnlUseCase).invoke(holdings)
        verify(calculateTotalPnlPercentageUseCase).invoke(expectedTotalPnl, expectedTotalInvestment)
    }

    @Test
    fun `should return correct portfolio summary with negative PnL`() {
        val holdings = listOf(mock(UserHolding::class.java))
        val expectedCurrentValue = 8000.0
        val expectedTotalInvestment = 10000.0
        val expectedTotalPnl = -2000.0
        val expectedTodayPnl = -300.0
        val expectedTotalPnlPercentage = -20.0

        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(expectedCurrentValue)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(expectedTotalInvestment)
        `when`(calculateTotalPnlUseCase(holdings)).thenReturn(expectedTotalPnl)
        `when`(calculateTodayPnlUseCase(holdings)).thenReturn(expectedTodayPnl)
        `when`(
            calculateTotalPnlPercentageUseCase(
                expectedTotalPnl,
                expectedTotalInvestment
            )
        ).thenReturn(expectedTotalPnlPercentage)

        val result = calculatePortfolioSummaryUseCase(holdings)

        assertEquals(expectedCurrentValue, result.currentValue)
        assertEquals(expectedTotalInvestment, result.totalInvestment)
        assertEquals(expectedTotalPnl, result.totalPnl)
        assertEquals(expectedTodayPnl, result.todayPnl)
        assertEquals(expectedTotalPnlPercentage, result.totalPnlPercentage)
    }

    @Test
    fun `should return correct portfolio summary with zero values`() {
        val holdings = emptyList<UserHolding>()
        val expectedCurrentValue = 0.0
        val expectedTotalInvestment = 0.0
        val expectedTotalPnl = 0.0
        val expectedTodayPnl = 0.0
        val expectedTotalPnlPercentage = 0.0

        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(expectedCurrentValue)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(expectedTotalInvestment)
        `when`(calculateTotalPnlUseCase(holdings)).thenReturn(expectedTotalPnl)
        `when`(calculateTodayPnlUseCase(holdings)).thenReturn(expectedTodayPnl)
        `when`(
            calculateTotalPnlPercentageUseCase(
                expectedTotalPnl,
                expectedTotalInvestment
            )
        ).thenReturn(expectedTotalPnlPercentage)

        val result = calculatePortfolioSummaryUseCase(holdings)

        assertEquals(expectedCurrentValue, result.currentValue)
        assertEquals(expectedTotalInvestment, result.totalInvestment)
        assertEquals(expectedTotalPnl, result.totalPnl)
        assertEquals(expectedTodayPnl, result.todayPnl)
        assertEquals(expectedTotalPnlPercentage, result.totalPnlPercentage)
    }

    @Test
    fun `should handle empty holdings list`() {
        val emptyHoldings = emptyList<UserHolding>()

        `when`(calculateCurrentValueUseCase(emptyHoldings)).thenReturn(0.0)
        `when`(calculateTotalInvestmentUseCase(emptyHoldings)).thenReturn(0.0)
        `when`(calculateTotalPnlUseCase(emptyHoldings)).thenReturn(0.0)
        `when`(calculateTodayPnlUseCase(emptyHoldings)).thenReturn(0.0)
        `when`(calculateTotalPnlPercentageUseCase(0.0, 0.0)).thenReturn(0.0)

        val result = calculatePortfolioSummaryUseCase(emptyHoldings)

        assertEquals(0.0, result.currentValue)
        assertEquals(0.0, result.totalInvestment)
        assertEquals(0.0, result.totalPnl)
        assertEquals(0.0, result.todayPnl)
        assertEquals(0.0, result.totalPnlPercentage)
    }

    @Test
    fun `should handle single holding`() {
        val singleHolding = listOf(mock(UserHolding::class.java))
        val expectedCurrentValue = 5000.0
        val expectedTotalInvestment = 4000.0
        val expectedTotalPnl = 1000.0
        val expectedTodayPnl = 100.0
        val expectedTotalPnlPercentage = 25.0

        `when`(calculateCurrentValueUseCase(singleHolding)).thenReturn(expectedCurrentValue)
        `when`(calculateTotalInvestmentUseCase(singleHolding)).thenReturn(expectedTotalInvestment)
        `when`(calculateTotalPnlUseCase(singleHolding)).thenReturn(expectedTotalPnl)
        `when`(calculateTodayPnlUseCase(singleHolding)).thenReturn(expectedTodayPnl)
        `when`(
            calculateTotalPnlPercentageUseCase(
                expectedTotalPnl,
                expectedTotalInvestment
            )
        ).thenReturn(expectedTotalPnlPercentage)

        val result = calculatePortfolioSummaryUseCase(singleHolding)

        assertEquals(expectedCurrentValue, result.currentValue)
        assertEquals(expectedTotalInvestment, result.totalInvestment)
        assertEquals(expectedTotalPnl, result.totalPnl)
        assertEquals(expectedTodayPnl, result.todayPnl)
        assertEquals(expectedTotalPnlPercentage, result.totalPnlPercentage)
    }

    @Test
    fun `should call all use cases exactly once with correct parameters`() {
        val holdings = listOf(mock(UserHolding::class.java), mock(UserHolding::class.java))
        val totalPnl = 1500.0
        val totalInvestment = 10000.0

        `when`(calculateCurrentValueUseCase(holdings)).thenReturn(11500.0)
        `when`(calculateTotalInvestmentUseCase(holdings)).thenReturn(totalInvestment)
        `when`(calculateTotalPnlUseCase(holdings)).thenReturn(totalPnl)
        `when`(calculateTodayPnlUseCase(holdings)).thenReturn(200.0)
        `when`(calculateTotalPnlPercentageUseCase(totalPnl, totalInvestment)).thenReturn(15.0)

        calculatePortfolioSummaryUseCase(holdings)

        verify(calculateCurrentValueUseCase, times(1)).invoke(holdings)
        verify(calculateTotalInvestmentUseCase, times(1)).invoke(holdings)
        verify(calculateTotalPnlUseCase, times(1)).invoke(holdings)
        verify(calculateTodayPnlUseCase, times(1)).invoke(holdings)
        verify(calculateTotalPnlPercentageUseCase, times(1)).invoke(totalPnl, totalInvestment)
    }

    @Test(expected = RuntimeException::class)
    fun `should propagate exception from calculateCurrentValueUseCase`() {
        val holdings = listOf(mock(UserHolding::class.java))

        `when`(calculateCurrentValueUseCase(holdings)).thenThrow(RuntimeException("Current value calculation failed"))

        calculatePortfolioSummaryUseCase(holdings)
    }
}