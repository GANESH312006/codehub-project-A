package com.example.codehubproject.bank.core

data class UserAccount(
    val user: String,
    val accountNo: Int,
    private val _transactions: MutableList<Transaction> = mutableListOf(),
    private var balance: Double = 27361.00
) {
    fun getTransactions(): List<Transaction> = _transactions.toList()
    fun addTransaction(transaction: Transaction) = _transactions.add(transaction)
    fun getBalance() = getTransactions().sumOf { it.amount } + balance
//        getTransactions().fold(0.0) { acc, transaction -> acc + transaction.amount } // Returns the same type as acc, in this acc type is Transaction
}