package com.example.codehubproject.login.ui

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LoginIconBtn(icon: ImageVector, modifier: Modifier) {
    IconButton(
        onClick = {
            Log.i("icon_btn", "clicked")
        },
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape),
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            imageVector = icon,
            contentDescription = null,
        )
    }
}