package com.kecc.effectivecourseschallenge.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kecc.data.local.CourseDbItem
import com.kecc.data.repo.CoursesRepoImp
import com.kecc.domain.interfaces.CoursesRepoItf
import com.kecc.domain.interfaces.DisplayableItemItf
import com.kecc.domain.models.CourseModel
import com.kecc.domain.usecases.GetCourses
import com.kecc.domain.usecases.GetDbCourses
import com.kecc.domain.usecases.InsertCourseToDb
import com.kecc.effectivecourseschallenge.databinding.ActivityMainBinding
import com.kecc.effectivecourseschallenge.databinding.FrgFavoritesBinding
import com.kecc.effectivecourseschallenge.databinding.FrgHomeBinding
import com.kecc.effectivecourseschallenge.databinding.RvItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val coursesRepo: CoursesRepoImp): ViewModel() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var homeBinding: FrgHomeBinding
    lateinit var favBinding: FrgFavoritesBinding


    suspend fun getCourses(): ArrayList<CourseModel> {
        return GetCourses(coursesRepo).execute()
    }

    suspend fun getDbCourses(): ArrayList<CourseModel> {
        return GetDbCourses(coursesRepo).execute()
    }

    val rvAdapter = ListDelegationAdapter<List<DisplayableItemItf>>(
        courseAdapterDelegate()
    )
    val rvItems: MutableLiveData<List<DisplayableItemItf>> by lazy {
        MutableLiveData<List<DisplayableItemItf>>()
    }

    val rvDbItems: MutableLiveData<List<DisplayableItemItf>> by lazy {
        MutableLiveData<List<DisplayableItemItf>>()
    }

    fun courseAdapterDelegate() = adapterDelegateViewBinding<
            CourseModel,
            DisplayableItemItf,
            RvItemBinding
        >(
        {
            layoutInflater, root -> RvItemBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            binding.txtTitle.text = item.title
            binding.btnFav.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    insertToDb(item)
                }
            }

        }
    }

    suspend fun insertToDb(model: CourseModel) {
        InsertCourseToDb(coursesRepo).execute(model)
    }

}