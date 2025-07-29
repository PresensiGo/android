package com.rizalanggoro.presensigo.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SelectedAvatars(
    maxAvatarsCount: Int = 3,
    itemsCount: Int,
    avatarSize: Int = 24
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box {
            Array(
                when (itemsCount < maxAvatarsCount) {
                    true -> itemsCount
                    else -> maxAvatarsCount
                }
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = (it * (avatarSize / 2)).dp)
                        .size((avatarSize + 4).dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(avatarSize.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .align(Alignment.Center)
                            .padding((avatarSize / 6).dp)
                    ) {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
        if (itemsCount > maxAvatarsCount)
            Text(
                "+ ${itemsCount - maxAvatarsCount} lainnya",
                style = MaterialTheme.typography.labelMedium
            )
    }
}