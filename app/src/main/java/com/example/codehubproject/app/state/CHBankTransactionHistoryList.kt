package com.example.codehubproject.app.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.codehubproject.app.components.CHBankTransactionHistoryText

object CHBankTransactionHistoryList {
    val CHBankTransactionHistoryList: SnapshotStateList<@Composable () -> Unit> =
        mutableStateListOf(
            {
                CHBankTransactionHistoryText(
                    timeText = "1/1/2025",
                    amountText = "123",
                    transferToUserName = null
                )
            },
            {
                CHBankTransactionHistoryText(
                    timeText = "1/2/2025",
                    amountText = "10456",
                    transferToUserName = null
                )
            },
            {
                CHBankTransactionHistoryText(
                    timeText = "1/3/2025",
                    amountText = "16782",
                    transferToUserName = null
                )
            },
        )

    fun addTransaction(
        amount: String,
        transferToUserName: String = "undefined",
        isLoan: Boolean = false,
        isDeposit: Boolean
    ) {
        CHBankTransactionHistoryList.add {
            CHBankTransactionHistoryText(
                timeText = "1/4/2025",
                amountText = amount,
                transferToUserName = transferToUserName,
                isLoan = isLoan,
                isDeposit = isDeposit
            )
        }
    }
}