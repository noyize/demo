package com.noyal.demo.core

import org.junit.Assert.assertEquals
import org.junit.Test

class NumberUtilTest {
    @Test
    fun `toCurrency formats positive numbers correctly`() {
        val value = 1234567.89
        val formatted = value.toCurrency()
        assertEquals("₹1,234,567.89", formatted)
    }

    @Test
    fun `toCurrency formats negative numbers correctly`() {
        val value = -1234.56
        val formatted = value.toCurrency()
        assertEquals("-₹1,234.56", formatted)
    }

    @Test
    fun `toCurrency formats zero correctly`() {
        val value = 0.0
        val formatted = value.toCurrency()
        assertEquals("₹0", formatted)
    }

    @Test
    fun `roundToDecimalPlaces rounds to given decimal places`() {
        val value = 3.14159
        val rounded = value.roundToDecimalPlaces(2)
        assertEquals(3.14, rounded, 0.0)
    }

    @Test
    fun `roundToDecimalPlaces handles zero decimal places`() {
        val value = 3.75
        val rounded = value.roundToDecimalPlaces(0)
        assertEquals(4.0, rounded, 0.0)
    }

    @Test
    fun `roundToDecimalPlaces handles negative numbers`() {
        val value = -2.718
        val rounded = value.roundToDecimalPlaces(1)
        assertEquals(-2.7, rounded, 0.0)
    }

    @Test
    fun `roundToDecimalPlaces returns 0 for NaN`() {
        val value = Double.NaN
        val rounded = value.roundToDecimalPlaces(2)
        assertEquals(0.0, rounded, 0.0)
    }

    @Test
    fun `roundToDecimalPlaces returns 0 for Infinity`() {
        val value = Double.POSITIVE_INFINITY
        val rounded = value.roundToDecimalPlaces(2)
        assertEquals(0.0, rounded, 0.0)
    }
}