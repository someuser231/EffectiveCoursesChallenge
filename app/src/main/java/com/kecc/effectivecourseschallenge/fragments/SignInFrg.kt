package com.kecc.effectivecourseschallenge.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.kecc.domain.usecases.SignIn
import com.kecc.effectivecourseschallenge.activities.MainActivity
import com.kecc.effectivecourseschallenge.databinding.FrgSigninBinding
import com.kecc.effectivecourseschallenge.view_models.SignInViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignInFrg : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    val signInViewModel: SignInViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInViewModel.signInBinding = FrgSigninBinding.inflate(layoutInflater)
        return signInViewModel.signInBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFrg().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = signInViewModel.signInBinding
        binding.btnSignIn.setOnClickListener {
            if (signInViewModel.checkSignIn(
                    binding.etxtLogin.text.toString(),
                    binding.etxtPassword.text.toString()
            ) && signInViewModel.checkEmail(
                    binding.etxtLogin.text.toString()
            )) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                requireActivity().startActivity(intent)
                requireActivity().finish()
            }
        }
        binding.ibtnVk.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, signInViewModel.getVkUri())
            requireActivity().startActivity(intent)
        }
        binding.ibtnOk.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, signInViewModel.getOkUri())
            requireActivity().startActivity(intent)
        }
    }
}