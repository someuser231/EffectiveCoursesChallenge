package com.kecc.effectivecourseschallenge.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kecc.effectivecourseschallenge.databinding.ActivitySignInBinding
import com.kecc.effectivecourseschallenge.fragments.SignInFrg
import com.kecc.effectivecourseschallenge.view_models.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {
    val signInViewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        signInViewModel.appBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInViewModel.appBinding.root)
        println(signInViewModel.appBinding)
        supportFragmentManager.beginTransaction()
            .replace(
                signInViewModel.appBinding.fragmentsHolder.id,
                SignInFrg()
            )
            .commit()
    }
}