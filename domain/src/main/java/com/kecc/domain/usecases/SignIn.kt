package com.kecc.domain.usecases

class SignIn(val login: String, val password: String) {
    fun execute(): Boolean {
        return login != "" && password != ""
    }
}