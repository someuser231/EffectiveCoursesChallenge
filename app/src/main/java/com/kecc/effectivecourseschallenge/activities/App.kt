package com.kecc.effectivecourseschallenge.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kecc.effectivecourseschallenge.databinding.AppLayoutBinding
import com.kecc.effectivecourseschallenge.fragments.SignInFrg
import com.kecc.effectivecourseschallenge.view_models.AppViewModel

class App : AppCompatActivity() {
    val appViewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
//        println(appViewModel.appBinding)
        appViewModel.appBinding = AppLayoutBinding.inflate(layoutInflater)
        setContentView(appViewModel.appBinding.root)
        println(appViewModel.appBinding)
        supportFragmentManager.beginTransaction()
            .replace(
                appViewModel.appBinding.fragmentsHolder.id,
                SignInFrg()
            )
            .commit()
    }
}