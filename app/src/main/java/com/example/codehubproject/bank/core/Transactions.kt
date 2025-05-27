package com.example.codehubproject.bank.core

import com.example.codehubproject.bank.enums.TransactionType

data class Transaction(
    val amount: Double,
    val type: TransactionType,
    val description: String,
)

