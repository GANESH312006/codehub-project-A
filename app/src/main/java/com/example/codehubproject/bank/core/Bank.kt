package com.example.codehubproject.bank.core

import com.example.codehubproject.bank.enums.TransactionType
import com.example.codehubproject.bank.exceptions.InsufficientBalanceError
import com.example.codehubproject.bank.exceptions.InvalidAmountError

interface BankOperations {
    fun deposit(amount: Double, description: String = "")
    fun withdraw(amount: Double, description: String = "")
    fun getBalance(): Double
}

class Bank(private val userAccount: UserAccount) : BankOperations {

    // Kotlin doesn't carry default param when used with interfaces, that's why we have to explicitly use.
    override fun withdraw(amount: Double, description: String) {
        if (amount < 0)
            throw InvalidAmountError("Enter positive amount!")

        if (amount > getBalance())
            throw InsufficientBalanceError("Insufficient balance for withdrawing money!")

        userAccount.addTransaction(
            Transaction(
                amount = -amount,
                description = description,
                type = TransactionType.WITHDRAWAL
            )
        )
    }

    override fun deposit(amount: Double, description: String) {
        if (amount < 0)
            throw InvalidAmountError("Enter positive amount!")

        userAccount.addTransaction(
            Transaction(
                amount = amount,
                description = description,
                type = TransactionType.DEPOSIT
            )
        )
    }

    override fun getBalance(): Double {
        return userAccount.getBalance()
    }
}