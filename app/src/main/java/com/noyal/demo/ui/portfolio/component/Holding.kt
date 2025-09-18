package com.noyal.demo.ui.portfolio.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.noyal.demo.core.toCurrency

@Composable
fun Holding(
    modifier: Modifier = Modifier,
    symbol: String,
    ltp: Double,
    quantity: Int,
    profitAndLose: Double
) {
    Column(modifier = modifier) {
        Column(Modifier.padding(16.dp)) {
            SymbolRow(
                symbol = symbol,
                ltp = ltp
            )

            Spacer(Modifier.height(16.dp))

            QuantityRow(
                quantity = quantity,
                profitAndLose = profitAndLose
            )
        }
        HorizontalDivider()
    }
}

@Composable
private fun SymbolRow(
    modifier: Modifier = Modifier,
    symbol: String,
    ltp: Double
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        ValueText(
            title = "LTP",
            value = ltp.toCurrency()
        )

    }
}

@Composable
fun QuantityRow(
    modifier: Modifier = Modifier,
    quantity: Int,
    profitAndLose: Double
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ValueText(
            title = "NET QTY",
            value = quantity.toString()
        )

        val valueColor = remember(profitAndLose) {
            if (profitAndLose > 0) Color(0xFF00796B)
            else if (profitAndLose < 0) Color(0xFFE53935)
            else Color(0xFF546E7A)
        }

        ValueText(
            title = "P&L",
            value = profitAndLose.toCurrency(),
            valueColor = valueColor
        )
    }
}

@Composable
private fun ValueText(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    titleColor: Color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val titleStyle = MaterialTheme.typography.bodySmall
    val bodyStyle = MaterialTheme.typography.bodyLarge

    val text = remember(value) {
        buildAnnotatedString {
            withStyle(titleStyle.toSpanStyle().copy(color = titleColor)) {
                append("$title: ")
            }
            withStyle(bodyStyle.toSpanStyle().copy(color = valueColor)) {
                append(value)
            }
        }
    }

    Text(modifier = modifier, text = text)
}


@Preview(showBackground = true)
@Composable
private fun HoldingPreview() {
    MaterialTheme {
        Holding(
            symbol = "ICICI",
            quantity = 100,
            ltp = 118.25,
            profitAndLose = 1221.0,
        )
    }
}