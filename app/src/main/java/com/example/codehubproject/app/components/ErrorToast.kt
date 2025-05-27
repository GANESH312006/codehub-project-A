package com.example.codehubproject.app.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowErrorToast(eMsg: String) {
    val context: Context = LocalContext.current
    LaunchedEffect(eMsg) {
        Toast.makeText(context, eMsg, Toast.LENGTH_SHORT).show()
    }
}