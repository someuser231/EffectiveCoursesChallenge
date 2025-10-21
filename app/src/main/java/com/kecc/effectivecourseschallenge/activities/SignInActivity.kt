package com.kecc.effectivecourseschallenge.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kecc.effectivecourseschallenge.databinding.ActivitySignInBinding
import com.kecc.effectivecourseschallenge.fragments.SignInFrg
import com.kecc.effectivecourseschallenge.view_models.SignInViewModel

class SignInActivity : AppCompatActivity() {
    val signInViewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInViewModel.appBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInViewModel.appBinding.root)
        supportFragmentManager.beginTransaction()
            .replace(
                signInViewModel.appBinding.fragmentsHolder.id,
                SignInFrg()
            )
            .commit()
    }
}