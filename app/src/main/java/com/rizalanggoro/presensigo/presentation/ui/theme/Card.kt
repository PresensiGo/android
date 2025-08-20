package com.rizalanggoro.presensigo.presentation.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object CardCornerShape {
    enum class Position {
        Single, Top, Center, Bottom
    }

    fun getPosition(index: Int, size: Int): Position = when (size == 1) {
        true -> Position.Single
        else -> when (index) {
            0 -> Position.Top
            size - 1 -> Position.Bottom
            else -> Position.Center
        }
    }

    @Composable
    fun getShape(position: Position): CornerBasedShape = when (position) {
        Position.Single -> MaterialTheme.shapes.large
        Position.Top -> RoundedCornerShape(
            topStart = MaterialTheme.shapes.large.topStart,
            topEnd = MaterialTheme.shapes.large.topEnd,
            bottomStart = MaterialTheme.shapes.extraSmall.bottomStart,
            bottomEnd = MaterialTheme.shapes.extraSmall.bottomEnd,
        )

        Position.Center -> MaterialTheme.shapes.extraSmall
        Position.Bottom -> RoundedCornerShape(
            bottomStart = MaterialTheme.shapes.large.bottomStart,
            bottomEnd = MaterialTheme.shapes.large.bottomEnd,
            topStart = MaterialTheme.shapes.extraSmall.topStart,
            topEnd = MaterialTheme.shapes.extraSmall.topEnd,
        )
    }
}
