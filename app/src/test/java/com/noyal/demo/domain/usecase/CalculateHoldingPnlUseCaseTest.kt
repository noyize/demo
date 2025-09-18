package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CalculateHoldingPnlUseCaseTest {

    private lateinit var calculateHoldingPnlUseCase: CalculateHoldingPnlUseCase

    @Before
    fun setUp() {
        calculateHoldingPnlUseCase = CalculateHoldingPnlUseCase()
    }

    @Test
    fun `should calculate positive PnL when LTP is higher than average price`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 10,
            ltp = 150.0,
            avgPrice = 100.0,
            close = 145.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(500.0, result, 0.01)
    }

    @Test
    fun `should calculate negative PnL when LTP is lower than average price`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 5,
            ltp = 80.0,
            avgPrice = 100.0,
            close = 85.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(-100.0, result, 0.01)
    }

    @Test
    fun `should calculate zero PnL when LTP equals average price`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 15,
            ltp = 100.0,
            avgPrice = 100.0,
            close = 98.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should handle zero quantity correctly`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 0,
            ltp = 150.0,
            avgPrice = 100.0,
            close = 140.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should handle negative quantity correctly`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = -10,
            ltp = 120.0,
            avgPrice = 100.0,
            close = 115.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(-200.0, result, 0.01)
    }

    @Test
    fun `should handle decimal values correctly`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 8,
            ltp = 125.75,
            avgPrice = 100.25,
            close = 120.50
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(204.0, result, 0.01)
    }

    @Test
    fun `should handle very small price differences`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 1000,
            ltp = 100.01,
            avgPrice = 100.00,
            close = 99.99
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(10.0, result, 0.01)
    }

    @Test
    fun `should handle large quantities and prices`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 1000,
            ltp = 2500.0,
            avgPrice = 2000.0,
            close = 2450.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(500000.0, result, 0.01)
    }

    @Test
    fun `should handle zero LTP`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 10,
            ltp = 0.0,
            avgPrice = 100.0,
            close = 95.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(-1000.0, result, 0.01)
    }

    @Test
    fun `should handle zero average price`() {
        val holding = UserHolding(
            symbol = "HDFC",
            quantity = 10,
            ltp = 100.0,
            avgPrice = 0.0,
            close = 95.0
        )

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(1000.0, result, 0.01)
    }

    @Test
    fun `should verify PnL calculation formula`() {
        val symbol = "HDFC"
        val quantity = 25
        val ltp = 175.5
        val avgPrice = 150.0
        val close = 170.0
        val holding = UserHolding(
            symbol = symbol,
            quantity = quantity,
            ltp = ltp,
            avgPrice = avgPrice,
            close = close
        )
        val expectedPnL = (ltp - avgPrice) * quantity

        val result = calculateHoldingPnlUseCase.invoke(holding)

        assertEquals(expectedPnL, result, 0.01)
        assertEquals(637.5, result, 0.01)
    }
}