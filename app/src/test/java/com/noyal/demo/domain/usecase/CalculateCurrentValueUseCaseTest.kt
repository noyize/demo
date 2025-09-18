package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculateCurrentValueUseCaseTest {

    private lateinit var useCase: CalculateCurrentValueUseCase

    @Before
    fun setUp() {
        useCase = CalculateCurrentValueUseCase()
    }

    @Test
    fun `should return zero for empty holdings list`() {
        val emptyHoldings = emptyList<UserHolding>()
        val result = useCase(emptyHoldings)
        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should calculate current value correctly for single holding`() {
        val holdings = listOf(
            UserHolding(
                symbol = "ICICI",
                quantity = 3,
                ltp = 119.10,
                avgPrice = 12.90,
                close = 118.0
            )
        )
        val result = useCase(holdings)
        val expected = 119.10 * 3
        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `should calculate current value correctly for multiple holdings`() {
        val holdings = listOf(
            UserHolding(
                symbol = "INFOSYS",
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
            ),
            UserHolding(
                symbol = "ICICIBANK",
                quantity = 1,
                ltp = 624.70,
                avgPrice = 135.60,
                close = 620.0
            )
        )

        val result = useCase(holdings)

        val expected = (119.10 * 3) + (2497.20 * 7) + (624.70 * 1)
        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `should handle zero quantity holdings`() {
        val holdings = listOf(
            UserHolding(
                symbol = "HDFC",
                quantity = 0,
                ltp = 100.0,
                avgPrice = 50.0,
                close = 95.0
            )
        )

        val result = useCase(holdings)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should handle zero ltp holdings`() {
        val holdings = listOf(
            UserHolding(
                symbol = "HDFC",
                quantity = 5,
                ltp = 0.0,
                avgPrice = 50.0,
                close = 95.0
            )
        )

        val result = useCase(holdings)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should handle decimal calculations correctly`() {
        val holdings = listOf(
            UserHolding(
                symbol = "HDFC",
                quantity = 3,
                ltp = 9.95,
                avgPrice = 2.79,
                close = 10.0
            )
        )

        val result = useCase(holdings)

        val expected = 9.95 * 3
        assertEquals(expected, result, 0.01)
    }
}