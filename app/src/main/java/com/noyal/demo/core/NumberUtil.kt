package com.noyal.demo.core

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Double.toCurrency(): String {
    val locale = Locale.Builder().setLanguage("en").setRegion("IN").build()
    val symbols = DecimalFormatSymbols(locale)
    val formatter = DecimalFormat("¤#,##0.##", symbols)
    formatter.currency = java.util.Currency.getInstance("INR")
    return formatter.format(this)
}
