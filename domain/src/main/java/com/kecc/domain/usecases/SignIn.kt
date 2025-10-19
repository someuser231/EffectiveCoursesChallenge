package com.kecc.domain.usecases

class SignIn(val login: String, val password: String) {
    fun execute(): Boolean {
        if (login != "" && password != "") return true
        return false
    }
}