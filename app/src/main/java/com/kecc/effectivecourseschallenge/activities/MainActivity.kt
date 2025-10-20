package com.kecc.effectivecourseschallenge.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kecc.effectivecourseschallenge.R
import com.kecc.effectivecourseschallenge.databinding.ActivityMainBinding
import com.kecc.effectivecourseschallenge.fragments.AccountFrg
import com.kecc.effectivecourseschallenge.fragments.FavoritesFrg
import com.kecc.effectivecourseschallenge.fragments.HomeFrg
import com.kecc.effectivecourseschallenge.view_models.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewModel.mainBinding.root)
        changeFragments(HomeFrg())
        val binding = mainViewModel.mainBinding

        binding.navBar.itemIconTintList = null
        binding.navBar.setOnItemSelectedListener { it ->
            when(it.itemId) {
                R.id.menu_home -> {
                    changeFragments(HomeFrg())
                    true
                }
                R.id.menu_favourites -> {
                    changeFragments(FavoritesFrg())
                    true
                }
                R.id.menu_account -> {
                    changeFragments(AccountFrg())
                    true
                }
                else -> false
            }
        }
    }

    fun changeFragments(frg: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(
                mainViewModel.mainBinding.fragmentsHolder.id,
                frg
            )
            .commit()
    }
}