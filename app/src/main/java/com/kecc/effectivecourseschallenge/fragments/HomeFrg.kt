package com.kecc.effectivecourseschallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kecc.domain.interfaces.DisplayableItemItf
import com.kecc.domain.models.CourseModel
import com.kecc.effectivecourseschallenge.R
import com.kecc.effectivecourseschallenge.databinding.FrgHomeBinding
import com.kecc.effectivecourseschallenge.databinding.RvItemBinding
import com.kecc.effectivecourseschallenge.view_models.MainViewModel

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

        val adapter = ListDelegationAdapter<List<DisplayableItemItf>>(
            courseAdapterDelegate()
        )
        val items: List<DisplayableItemItf> = listOf(
            CourseModel(
                1,
                "test",
                "",
                "",
                "",
                "",
                false,
                ""
            ),
            CourseModel(
                2,
                "test2",
                "",
                "",
                "",
                "",
                false,
                ""
            ),
            CourseModel(
                2,
                "test3",
                "",
                "",
                "",
                "",
                false,
                ""
            )
        )

        adapter.items = items
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHome.adapter = adapter
    }

    fun courseAdapterDelegate() = adapterDelegateViewBinding<CourseModel, DisplayableItemItf, RvItemBinding>(
        { layoutInflater, root -> RvItemBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.txtTitle.text = item.title
        }
    }
}