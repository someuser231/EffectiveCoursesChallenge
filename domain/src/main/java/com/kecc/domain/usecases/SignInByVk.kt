package com.kecc.domain.usecases

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

class SignInByVk() {
    fun execute(): Uri {
        return "https://vk.com".toUri()
    }
}