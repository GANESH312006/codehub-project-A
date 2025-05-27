package com.example.codehubproject.login.viewmodel

import androidx.lifecycle.ViewModel
import com.example.codehubproject.login.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _isError = MutableStateFlow<Boolean>(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()


    fun login(password: String) {
        _loginState.value = LoginState.Loading
        _isError.value = false

        if (password == "pwd")
            _loginState.value = LoginState.Success
        else {
            _loginState.value = LoginState.Error("Invalid Credentials")
            _isError.value = true
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}