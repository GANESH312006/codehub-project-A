package com.example.codehubproject.app.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object AccountClosedState {
    val isAccountClosed: MutableState<Boolean> = mutableStateOf(false)
}