@file:Suppress("DEPRECATION")

package com.example.codehubproject.app

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.codehubproject.R
import com.example.codehubproject.app.components.CHBankBalance
import com.example.codehubproject.app.components.CHBankGreetingText
import com.example.codehubproject.app.components.CHBankOperations
import com.example.codehubproject.app.components.CHBankSummaryText
import com.example.codehubproject.app.components.CHBankTransactionHistory
import com.example.codehubproject.app.components.ShowErrorToast
import com.example.codehubproject.app.state.AccountClosedState
import com.example.codehubproject.app.state.CHBankTransactionHistoryList
import com.example.codehubproject.bank.core.Bank
import com.example.codehubproject.bank.exceptions.InsufficientBalanceError
import com.example.codehubproject.bank.session.UserSession
import com.example.codehubproject.navigation.Routes
import com.example.codehubproject.ui.theme.CloseAccountColor1
import com.example.codehubproject.ui.theme.CloseAccountColor2
import com.example.codehubproject.ui.theme.ColorPrimaryDarker
import com.example.codehubproject.ui.theme.ColorPrimaryGreen
import com.example.codehubproject.ui.theme.TransferColor1
import com.example.codehubproject.ui.theme.TransferColor2

private fun getBankAccount(): Bank {
    return Bank(UserSession.currentAcconut)
}

private fun logTransferTransaction(
    transferAmount: String,
    transferTo: String?,
    isDeposit: Boolean,
) {
    val bankingTransactionHistory = CHBankTransactionHistoryList
    bankingTransactionHistory.addTransaction(
        amount = transferAmount,
        transferToUserName = transferTo?.replaceFirstChar { it.uppercaseChar() } ?: "",
        isDeposit = isDeposit
    )
}

@Composable
fun CHBankApp(modifier: Modifier = Modifier, text: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 0.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {

        // Transfer operation
        val transferTo: MutableState<String> = remember { mutableStateOf("") }
        val transferAmount: MutableState<String> = remember { mutableStateOf("") }

        // Request loan operation
        val requestLoanAmount: MutableState<String> = remember { mutableStateOf("") }

        // Close Account operation
        val confirmUser: MutableState<String> = remember { mutableStateOf("") }
        val confirmPin: MutableState<String> = remember { mutableStateOf("") }

        // User account balance
        var userAccountBalance: MutableState<String> = remember { mutableStateOf("27361.0") }

        // User Account
        val userBankAccount: Bank = getBankAccount()

        // Error and Show error logic
        var errorMsg: MutableState<String?> = remember { mutableStateOf(null) }
        errorMsg.value?.let { msg ->
            ShowErrorToast(msg)
            errorMsg.value = null
        }

        // Account closed Manipulation
        val accountClosedState = AccountClosedState.isAccountClosed

        CHBankGreetingText(text = text)
        Log.i("user_balance", userAccountBalance.value)
        CHBankBalance(amount = userAccountBalance.value + "$")
        CHBankTransactionHistory()

        CHBankSummaryText(
            inAmount = "7400.00",
            outAmount = "378.00",
            interest = "74.00",
            modifier = Modifier.offset(y = -90.dp)
        )

        // Operations -> Transfer money
        CHBankOperations(
            linearGradientColor1 = TransferColor1,
            linearGradientColor2 = TransferColor2,
            operationText = R.string.transfer_money,
            placeHolderText1 = R.string.transfer_to,
            placeHolderText2 = R.string.amount,
            modifier = Modifier
                .offset(y = (-80).dp)
                .padding(bottom = 20.dp),
            textState1 = transferTo,
            textState2 = transferAmount
        ) {
            try {
                userBankAccount.withdraw(amount = transferAmount.value.toDouble())
                // To update the bank balance label
                userAccountBalance.value = userBankAccount.getBalance().toString()

                logTransferTransaction(
                    transferAmount = "-${transferAmount.value}",
                    transferTo = transferTo.value,
                    isDeposit = false
                )
            } catch (e: InsufficientBalanceError) {
                errorMsg.value = e.message.toString()
            }
        }

        // Operations -> Request Loan
        CHBankOperations(
            linearGradientColor1 = ColorPrimaryDarker,
            linearGradientColor2 = ColorPrimaryGreen,
            operationText = R.string.request_loan,
            placeHolderText1 = R.string.amount,
            placeHolderText2 = null,
            modifier = Modifier
                .offset(y = (-80).dp)
                .padding(bottom = 20.dp),
            textState1 = requestLoanAmount,
            textState2 = null
        ) {
            userBankAccount.deposit(amount = requestLoanAmount.value.toDouble())

            // To update the bank balance label
            userAccountBalance.value = userBankAccount.getBalance().toString()

            logTransferTransaction(
                transferAmount = requestLoanAmount.value,
                transferTo = null,
                isDeposit = true
            )
        }

        // Operations -> Close account.
        CHBankOperations(
            linearGradientColor1 = CloseAccountColor1,
            linearGradientColor2 = CloseAccountColor2,
            operationText = R.string.close_account,
            placeHolderText1 = R.string.confirm_user,
            placeHolderText2 = R.string.confirm_pin,
            modifier = Modifier.offset(y = (-80).dp),
            textState1 = confirmUser,
            textState2 = confirmPin
        ) {
            if (confirmPin.value.toInt() == 1111 && confirmUser.value == text) {
                navController.navigate(Routes.LoginScreen.route) {
                    popUpTo(Routes.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }

            accountClosedState.value = true
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}