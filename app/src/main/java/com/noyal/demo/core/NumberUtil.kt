package com.noyal.demo.core

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.pow
import kotlin.math.round

fun Double.toCurrency(): String {
    val locale = Locale.Builder().setLanguage("en").setRegion("IN").build()
    val symbols = DecimalFormatSymbols(locale)
    val formatter = DecimalFormat("¤#,##0.##", symbols)
    formatter.currency = java.util.Currency.getInstance("INR")
    return formatter.format(this)
}

fun Double.roundToDecimalPlaces(decimalPlaces: Int): Double {
    return if (this.isNaN() || this.isInfinite()) {
        0.0
    } else {
        val multiplier = 10.0.pow(decimalPlaces.toDouble())
        round(this * multiplier) / multiplier
    }
}
