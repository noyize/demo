package com.noyal.demo.domain.usecase

import com.noyal.demo.domain.model.UserHolding
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculateTodayPnlUseCaseTest {

    private lateinit var useCase: CalculateTodayPnlUseCase

    @Before
    fun setUp() {
        useCase = CalculateTodayPnlUseCase()
    }

    @Test
    fun `should return zero for empty holdings list`() {
        val emptyHoldings = emptyList<UserHolding>()

        val result = useCase(emptyHoldings)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `should calculate positive today PnL when close is greater than ltp`() {

        val holdings = listOf(
            UserHolding(
                symbol = "TEST",
                quantity = 10,
                ltp = 95.0,
                avgPrice = 50.0,
                close = 100.0
            )
        )

        val result = useCase(holdings)

        val expected = (100.0 - 95.0) * 10
        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `should calculate negative today PnL when close is less than ltp`() {
        val holdings = listOf(
            UserHolding(
                symbol = "TEST",
                quantity = 5,
                ltp = 105.0,
                avgPrice = 50.0,
                close = 100.0
            )
        )

        val result = useCase(holdings)

        val expected = (100.0 - 105.0) * 5
        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `should return zero when close equals ltp`() {
        val holdings = listOf(
            UserHolding(
                symbol = "TEST",
                quantity = 10,
                ltp = 100.0,
                avgPrice = 50.0,
                close = 100.0
            )
        )

        val result = useCase(holdings)

        assertEquals(0.0, result, 0.01)
    }

    @Test
    fun `invoke should calculate correctly for multiple holdings`() {
        val holdings = listOf(
            UserHolding(
                symbol = "STOCK1",
                quantity = 10,
                ltp = 95.0,
                avgPrice = 50.0,
                close = 100.0
            ),
            UserHolding(
                symbol = "STOCK2",
                quantity = 5,
                ltp = 105.0,
                avgPrice = 60.0,
                close = 100.0
            ),
            UserHolding(
                symbol = "STOCK3",
                quantity = 2,
                ltp = 200.0,
                avgPrice = 150.0,
                close = 210.0
            )
        )

        val result = useCase(holdings)

        val expected = ((100.0 - 95.0) * 10) + ((100.0 - 105.0) * 5) + ((210.0 - 200.0) * 2)

        assertEquals(expected, result, 0.01)
    }

    @Test
    fun `invoke should handle zero quantity`() {
        val holdings = listOf(
            UserHolding(
                symbol = "TEST",
                quantity = 0,
                ltp = 95.0,
                avgPrice = 50.0,
                close = 100.0
            )
        )

        val result = useCase(holdings)

        assertEquals(0.0, result, 0.01)
    }
}