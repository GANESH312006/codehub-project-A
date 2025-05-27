package com.example.codehubproject.login.ui

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.LocalPhone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codehubproject.R
import com.example.codehubproject.app.state.AccountClosedState
import com.example.codehubproject.bank.core.Bank
import com.example.codehubproject.bank.core.UserAccount
import com.example.codehubproject.bank.session.UserSession
import com.example.codehubproject.login.Username
import com.example.codehubproject.login.state.LoginState
import com.example.codehubproject.login.viewmodel.LoginViewModel
import com.example.codehubproject.navigation.Routes

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    var passWord: MutableState<String> = remember { mutableStateOf("") }
    var userName: MutableState<String> = remember { mutableStateOf("") }

    val loginState by viewModel<LoginViewModel>().loginState.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val accountNumber: Int = 1000
    Username.userName = userName.value

    // Closed Account state
    val closedAccountState: MutableState<Boolean> = AccountClosedState.isAccountClosed
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFFAF0))
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            LoginText(
                text = R.string.welcome_back_login,
                fontSize = 24.sp,
                textAlign = null,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 9.dp)
            )

            LoginText(
                text = R.string.sign_in_to_access_your_banking_account,
                fontSize = 15.sp,
                fontWeight = null,
                modifier = Modifier.run {
                    fillMaxWidth()
                        .padding(horizontal = 79.dp)
                        .padding(bottom = 4.dp)
                }
            )

            Spacer(Modifier.padding(16.dp))

            LoginText(
                text = R.string.user_name_login,
                fontSize = null,
                fontWeight = null,
                modifier = Modifier.run {
                    fillMaxWidth()
                        .padding(horizontal = 68.dp)
                        .padding(bottom = 4.dp)
                }
            )

            InputField(
                value = userName.value,
                onValueChanged = { userName.value = it },
                text = "Enter username",
                isError = isError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isPassword = false
            )

            Spacer(modifier = Modifier.padding(bottom = 30.dp))

            LoginText(
                text = R.string.password,
                textAlign = TextAlign.Left,
                fontSize = null,
                fontWeight = null,
                modifier = Modifier.run {
                    fillMaxWidth()
                        .padding(horizontal = 68.dp)
                        .padding(bottom = 4.dp)
                }
            )

            InputField(
                value = passWord.value,
                onValueChanged = { passWord.value = it },
                isError = isError,
                text = "Enter password",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (loginState) {
                is LoginState.Loading -> CircularProgressIndicator()
                is LoginState.Error -> Text(
                    text = (loginState as LoginState.Error).message,
                    color = Color.Red
                )

                is LoginState.Success -> Text(
                    text = "Login Successful!",
                    color = Color.Green
                )

                else -> {}
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (closedAccountState.value) {
                        Toast.makeText(context, "Account Not Found!", Toast.LENGTH_SHORT).show()
                        Log.i("account_closed", "Pass!")
                        return@Button
                    }
                    Log.i("account_not_closed", closedAccountState.value.toString())
                    viewModel.login(passWord.value)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5EC576)
                ), elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Login,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text("Login")
            }

            if (loginState is LoginState.Success) {
                LaunchedEffect(Unit) {
                    UserSession.currentAcconut =
                        UserAccount(user = Username.userName ?: "no_name", accountNo = 1111)
                    UserSession.bank = Bank(UserSession.currentAcconut)

                    navController.navigate(
                        Routes.CHBankScreen.route + "/${
                            userName.value.replaceFirstChar { it.uppercaseChar() }
                        }"
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp),
                    color = Color.Gray.copy(alpha = 0.5f),
                    thickness = 1.dp
                )

                Text(
                    text = "Or Continue With",
                    modifier = Modifier.padding(12.dp)
                )

                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp),
                    color = Color.Gray.copy(alpha = 0.5f),
                    thickness = 1.dp
                )
            }

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LoginIconBtn(
                        icon = Icons.Filled.Facebook,
                        modifier = Modifier.padding(start = 70.dp)
                    )
                    LoginIconBtn(
                        icon = Icons.Filled.LocalPhone,
                        modifier = Modifier
                    )
                    LoginIconBtn(
                        icon = Icons.Filled.Email,
                        modifier = Modifier.padding(end = 70.dp)
                    )
                }
            }
        }
    }

//    println(userAccounts.value)
}

@Composable
fun LoginText(
    fontSize: TextUnit?,
    textAlign: TextAlign? = TextAlign.Left,
    fontWeight: FontWeight?,
    modifier: Modifier,
    @StringRes text: Int
) {
    Text(
        text = stringResource(text),
        textAlign = textAlign ?: TextAlign.Unspecified,
        modifier = modifier,
        fontSize = fontSize ?: TextUnit.Unspecified,
        fontWeight = fontWeight
    )
}

@Composable
fun InputField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean,
    text: String,
    keyboardOptions: KeyboardOptions,
    isPassword: Boolean
) {
    var showPassword: MutableState<Boolean> = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier.offset(x = -(5).dp),
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        isError = isError,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFF5EC576),
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        placeholder = { Text(text = text) },
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = {
                        showPassword.value = !showPassword.value
                    }
                ) {
                    Icon(
                        imageVector = if (showPassword.value) {
                            Icons.Filled.Visibility
                        } else
                            Icons.Filled.VisibilityOff,
                        contentDescription = if (showPassword.value) "Hide password" else "Show password"
                    )
                }
            }
        },
        visualTransformation = if (isPassword && !showPassword.value)
            PasswordVisualTransformation()
        else VisualTransformation.None
    )
}