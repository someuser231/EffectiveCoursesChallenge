package com.kecc.domain.usecases

import android.net.Uri
import androidx.core.net.toUri

class SignInByVk() {
    fun execute(): Uri {
        return "https://vk.com".toUri()
    }
}