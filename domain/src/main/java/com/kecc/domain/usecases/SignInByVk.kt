package com.kecc.domain.usecases

import android.app.Activity
import android.content.Intent
import androidx.core.net.toUri

class SignInByVk(val activity: Activity) {
    fun execute() {
        val intent = Intent(Intent.ACTION_VIEW, "https://vk.com".toUri())
        activity.startActivity(intent)
    }
}