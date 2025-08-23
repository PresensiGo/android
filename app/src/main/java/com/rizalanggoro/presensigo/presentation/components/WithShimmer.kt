package com.rizalanggoro.presensigo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import com.valentinilk.shimmer.shimmer

@Composable
fun WithShimmer(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    when (isLoading) {
        true -> Box(
            modifier = modifier
                .shimmer()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.outlineVariant)
        ) {
            Box(modifier = Modifier.alpha(0f)) {
                content()
            }
        }

        else -> content()
    }
}
