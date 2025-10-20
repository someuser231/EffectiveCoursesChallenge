package com.kecc.effectivecourseschallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kecc.effectivecourseschallenge.R
import com.kecc.effectivecourseschallenge.databinding.FrgFavoritesBinding
import com.kecc.effectivecourseschallenge.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.getValue

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavoritesFrg : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    val mainViewModel: MainViewModel by activityViewModels()

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
        mainViewModel.favBinding = FrgFavoritesBinding.inflate(layoutInflater)
        return mainViewModel.favBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFrg().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = mainViewModel.favBinding
        binding.rvFav.layoutManager = LinearLayoutManager(requireContext())

        binding.rvFav.adapter = mainViewModel.rvAdapter

        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.rvDbItems.postValue(mainViewModel.getDbCourses())
        }

        mainViewModel.rvDbItems.observe(viewLifecycleOwner) {
            mainViewModel.rvAdapter.items = it
            mainViewModel.rvAdapter.notifyDataSetChanged()
            if (it != null) binding.progressBar.isVisible = false
        }
    }
}