package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculateTotalInvestmentUseCaseTest {

    private lateinit var useCase: CalculateTotalInvestmentUseCase

    @Before
    fun setUp() {
        useCase = CalculateTotalInvestmentUseCase()
    }

    @Test
    fun `should return zero for empty holdings list`() {
        val emptyHoldings = emptyList<UserHolding>()

        val result = useCase(emptyHoldings)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should calculate total investment correctly for single holding`() {
        val holdings = listOf(
            UserHolding(
                symbol = "HDFC",
                quantity = 3,
                ltp = 119.10,
                avgPrice = 12.90,
                close = 118.0
            )
        )

        val result = useCase(holdings)

        val expected = 12.90 * 3
        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `should calculate total investment correctly for multiple holdings`() {
        val holdings = listOf(
            UserHolding(
                symbol = "ASHOKLEY",
                quantity = 3,
                ltp = 119.10,
                avgPrice = 12.90,
                close = 118.0
            ),
            UserHolding(
                symbol = "HDFC",
                quantity = 7,
                ltp = 2497.20,
                avgPrice = 2733.20,
                close = 2500.0
            )
        )


        val result = useCase(holdings)

        val expected = (12.90 * 3) + (2733.20 * 7)
        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `should handle zero quantity holdings`() {
        val holdings = listOf(
            UserHolding(
                symbol = "TEST",
                quantity = 0,
                ltp = 100.0,
                avgPrice = 50.0,
                close = 95.0
            )
        )

        val result = useCase(holdings)

        assertEquals(0.0, result, 0.01)
    }
}