package com.kecc.domain.usecases

import android.net.Uri
import androidx.core.net.toUri

class SignInByOk() {
    fun execute(): Uri {
        return "https://ok.ru".toUri()
    }
}