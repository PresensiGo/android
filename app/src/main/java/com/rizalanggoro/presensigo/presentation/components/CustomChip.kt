package com.rizalanggoro.presensigo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun CustomChip(
    isLoading: Boolean = false,
    isSelected: Boolean = false,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Text(
        when (isLoading) {
            true -> "loading"
            else -> text
        },
        style = MaterialTheme.typography.bodyMedium,
        color = when (isLoading) {
            true -> MaterialTheme.colorScheme.outlineVariant
            else -> Color.Unspecified
        },
        modifier = Modifier
            .then(
                when (isLoading) {
                    true -> Modifier.shimmer()
                    else -> Modifier.border(
                        1.dp,
                        when (isSelected) {
                            true -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .48f)
                            else -> MaterialTheme.colorScheme.outlineVariant
                        },
                        CircleShape
                    )
                }
            )
            .clip(CircleShape)
            .background(
                when (isLoading) {
                    true -> MaterialTheme.colorScheme.outlineVariant
                    else -> when (isSelected) {
                        true -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .24f)
                        else -> Color.Unspecified
                    }
                }
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}