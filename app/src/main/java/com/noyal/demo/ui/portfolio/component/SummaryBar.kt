package com.noyal.demo.ui.portfolio.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.noyal.demo.core.toCurrency
import com.noyal.demo.ui.theme.green600
import com.noyal.demo.ui.theme.grey600
import com.noyal.demo.ui.theme.red600

@Composable
fun SummaryBar(
    modifier: Modifier = Modifier,
    currentValue: Double,
    totalInvestment: Double,
    todayProfitAndLoss: Double,
    totalProfitAndLoss: Double,
    isExpanded: Boolean,
    shape: Shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp
    ),
    onExpand: (Boolean) -> Unit = {}
) {

    Column(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) { onExpand(!isExpanded) }) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 1.dp)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                AnimatedVisibility(isExpanded) {
                    Column {
                        ValueRow(
                            title = "Current value*",
                            value = currentValue.toCurrency()
                        )

                        Spacer(Modifier.height(24.dp))

                        ValueRow(
                            title = "Total investment*",
                            value = totalInvestment.toCurrency(),
                        )

                        Spacer(Modifier.height(24.dp))

                        val valueColor = remember(todayProfitAndLoss) {
                            if (todayProfitAndLoss > 0) green600
                            else if (todayProfitAndLoss < 0) red600
                            else grey600
                        }

                        ValueRow(
                            title = "Today's Profit & Loss*",
                            value = todayProfitAndLoss.toCurrency(),
                            valueColor = valueColor
                        )

                        Spacer(Modifier.height(16.dp))

                        HorizontalDivider()

                        Spacer(Modifier.height(16.dp))
                    }
                }

                val totalValueColor = remember(totalProfitAndLoss) {
                    if (todayProfitAndLoss > 0) green600
                    else if (todayProfitAndLoss < 0) red600
                    else grey600
                }

                ValueRow(
                    title = "Profit & Loss*",
                    value = totalProfitAndLoss.toCurrency(),
                    valueColor = totalValueColor,
                    titleTrailing = {
                        val rotationAngle by animateFloatAsState(
                            targetValue = if (isExpanded) 180f else 0f,
                            label = "arrow_rotation"
                        )

                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowUp,
                            contentDescription = "Expand or collapse icon",
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(rotationAngle)
                        )
                    }
                )

            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(0.8f))
    }

}


@Composable
private fun ValueRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    titleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    valueStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    titleTrailing: @Composable (() -> Unit)? = null,
    valueTrailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = title,
                style = titleStyle,
                color = titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            titleTrailing?.let { it() }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                style = valueStyle,
                color = valueColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            valueTrailing?.let { it() }
        }

    }
}

@Preview
@Composable
private fun SummaryBarPreview() {
    MaterialTheme {

        var isExpanded by remember { mutableStateOf(false) }
        SummaryBar(
            currentValue = 327472.22,
            totalInvestment = 429239.23,
            todayProfitAndLoss = 29239.23,
            totalProfitAndLoss = 839239.23,
            isExpanded = isExpanded,
            onExpand = {
                isExpanded = it
            }
        )
    }
}