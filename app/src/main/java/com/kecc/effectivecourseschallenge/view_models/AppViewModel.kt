package com.kecc.effectivecourseschallenge.view_models

import androidx.lifecycle.ViewModel
import com.kecc.effectivecourseschallenge.databinding.AppLayoutBinding
import com.kecc.effectivecourseschallenge.databinding.FrgHomeBinding
import com.kecc.effectivecourseschallenge.databinding.FrgSigninBinding

class AppViewModel: ViewModel() {
    lateinit var appBinding: AppLayoutBinding
    lateinit var signInBinding: FrgSigninBinding
    lateinit var homeBinding: FrgHomeBinding
}