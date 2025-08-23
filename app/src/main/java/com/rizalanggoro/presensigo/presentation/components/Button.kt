package com.rizalanggoro.presensigo.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    text: String,
    onClick: () -> Unit = {},
) {
    when (isLoading) {
        true -> Box(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            LoadingIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        else -> Button(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = onClick,
        ) {
            Text(text)
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    OutlinedButton(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        onClick = onClick
    ) {
        Text(text)
    }
}