package com.noyal.demo.core

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.pow
import kotlin.math.round

fun Double.toCurrency(): String {
    return when {
        this == 0.0 -> "₹0"
        this < 0 -> "-₹${formatCurrencyAmount(-this)}"
        else -> "₹${formatCurrencyAmount(this)}"
    }
}

private fun formatCurrencyAmount(amount: Double): String {
    val locale = Locale.Builder().setLanguage("en").setRegion("IN").build()
    val formatter = DecimalFormat("#,##0.##", DecimalFormatSymbols(locale))
    return formatter.format(amount)
}

fun Double.roundToDecimalPlaces(decimalPlaces: Int): Double {
    return if (this.isNaN() || this.isInfinite()) {
        0.0
    } else {
        val multiplier = 10.0.pow(decimalPlaces.toDouble())
        round(this * multiplier) / multiplier
    }
}
