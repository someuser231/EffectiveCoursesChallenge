package com.kecc.effectivecourseschallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kecc.data.repo.CoursesRepoImp
import com.kecc.domain.interfaces.DisplayableItemItf
import com.kecc.domain.models.CourseModel
import com.kecc.domain.usecases.GetCourses
import com.kecc.effectivecourseschallenge.databinding.FrgHomeBinding
import com.kecc.effectivecourseschallenge.databinding.RvItemBinding
import com.kecc.effectivecourseschallenge.view_models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFrg : Fragment() {
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
        mainViewModel.homeBinding = FrgHomeBinding.inflate(layoutInflater)
        return mainViewModel.homeBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFrg().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = mainViewModel.homeBinding
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())

        binding.rvHome.adapter = mainViewModel.rvAdapter

        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.rvItems.postValue(mainViewModel.getCourses())

        }


        mainViewModel.rvItems.observe(viewLifecycleOwner) {
            mainViewModel.rvAdapter.items = it
            mainViewModel.rvAdapter.notifyDataSetChanged()
        }

        var sortByDate: Boolean = false
        binding.ibtnFilter.setOnClickListener {
            sortByDate = !sortByDate
            mainViewModel.sortCourses(sortByDate)
        }
    }


}