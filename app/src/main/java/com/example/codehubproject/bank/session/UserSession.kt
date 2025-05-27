package com.example.codehubproject.bank.session

import com.example.codehubproject.bank.core.Bank
import com.example.codehubproject.bank.core.UserAccount

object UserSession {
    lateinit var currentAcconut: UserAccount
    lateinit var bank: Bank
}