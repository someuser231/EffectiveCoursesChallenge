package com.kecc.effectivecourseschallenge.view_models

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.kecc.domain.usecases.SignIn
import com.kecc.domain.usecases.SignInByOk
import com.kecc.domain.usecases.SignInByVk
import com.kecc.effectivecourseschallenge.databinding.ActivitySignInBinding
import com.kecc.effectivecourseschallenge.databinding.FrgSigninBinding

class SignInViewModel(): ViewModel() {
    lateinit var appBinding: ActivitySignInBinding
    lateinit var signInBinding: FrgSigninBinding

    fun checkSignIn(login: String, password: String): Boolean {
        return SignIn(login, password).execute()
    }
    fun getVkUri(): Uri {
        return SignInByVk().execute()
    }
    fun getOkUri(): Uri {
        return SignInByOk().execute()
    }

    fun checkEmail(mail: String): Boolean {
        val regex = Regex("[^А-Яа-я]+@[^А-Яа-я]+.[^А-Яа-я]+")
        return regex.matches(mail)
    }
}