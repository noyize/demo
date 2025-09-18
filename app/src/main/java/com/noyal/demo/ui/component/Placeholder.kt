package com.noyal.demo.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun Placeholder(
    modifier: Modifier = Modifier,
    text: String,
    imageVector: ImageVector,
    action: @Composable (() -> Unit)? = null
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val baseSize by remember {
            derivedStateOf {
                minOf(maxWidth, maxHeight) * 0.5f
            }
        }

        Column(
            modifier = modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(baseSize)
                    .aspectRatio(1f),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "Placeholder image",
                        modifier = Modifier.fillMaxSize(0.6f)
                    )
                }
            }

            Text(
                modifier = Modifier.width(baseSize * 1.5f), // scale text area
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            action?.let { it() }
        }
    }
}


@Composable
fun ComingSoonPlaceHolder(modifier: Modifier = Modifier) {
    Placeholder(
        modifier = modifier,
        text = "Good things take time. This feature is on the way!",
        imageVector = Icons.Filled.Construction
    )
}

@Composable
fun ErrorPlaceHolder(
    modifier: Modifier = Modifier,
    message: String? = null,
    retry: () -> Unit = {}
) {
    Placeholder(
        modifier = modifier,
        text = message ?: "A small hiccup, give it another go?",
        imageVector = Icons.Filled.HourglassTop,
        action = {
            Button(onClick = {retry()}) {
                Text("Retry")
            }
        }
    )
}