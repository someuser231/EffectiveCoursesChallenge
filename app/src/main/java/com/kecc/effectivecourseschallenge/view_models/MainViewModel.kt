package com.kecc.effectivecourseschallenge.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coil.load
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kecc.data.repo.CoursesRepoImp
import com.kecc.domain.interfaces.DisplayableItemItf
import com.kecc.domain.models.CourseModel
import com.kecc.domain.usecases.DeleteCourseFromDb
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
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel(private val coursesRepo: CoursesRepoImp): ViewModel() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var homeBinding: FrgHomeBinding
    lateinit var favBinding: FrgFavoritesBinding

    fun sortCourses(byDate: Boolean) {
        val curValues = rvItems.value
        if (curValues != null) {
            if (byDate) {
                val result = curValues.sortedByDescending {
                    when (it) {
                        is CourseModel -> {
                            it.publishDate
                        }
                        else -> null
                    }
                }
                rvItems.postValue(result)
            }
            else {
                val result = curValues.sortedWith (
                    compareBy<DisplayableItemItf> {
                        (it as CourseModel)?.id ?: Int.MAX_VALUE
                    }
                )
                rvItems.postValue(result)
            }
        }
    }
    suspend fun getCourses(): ArrayList<CourseModel> {

        val items = GetCourses(coursesRepo).execute()
        for (i in items) {
            if (i.hasLike) {
                insertToDb(i)
            }
        }
        return items
    }
    suspend fun getDbCourses(): ArrayList<CourseModel> {
        return GetDbCourses(coursesRepo).execute()
    }
    suspend fun insertToDb(model: CourseModel) {
        InsertCourseToDb(coursesRepo).execute(model)
    }
    suspend fun deleteFromDb(itemId: Int) {
        DeleteCourseFromDb(coursesRepo).execute(itemId)
        rvDbItems.postValue(getDbCourses())
    }

    val isConnected = MutableLiveData<Boolean>()


    val rvAdapter = ListDelegationAdapter<List<DisplayableItemItf>>(
        courseAdapterDelegate()
    )
    val curRvItems: MutableLiveData<List<DisplayableItemItf>> by lazy {
        MutableLiveData<List<DisplayableItemItf>>()
    }
    val rvItems: MutableLiveData<List<DisplayableItemItf>> by lazy {
        MutableLiveData<List<DisplayableItemItf>>()
    }

    val rvDbItems: MutableLiveData<List<DisplayableItemItf>> by lazy {
        MutableLiveData<List<DisplayableItemItf>>()
    }

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
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
            binding.txtDesc.text = item.text
            binding.txtPrice.text = item.price + " ₽"
            binding.txtRate.text = item.rate
            binding.chkboxFav.isChecked = item.hasLike

            val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                .format(dateFormat.parse(item.publishDate))
            binding.txtDate.text = date

            val imageUrl = "https://placebear.com/400/150"
            binding.imgMain.load(imageUrl) {
                crossfade(true)
            }


            binding.chkboxFav.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    when (item.hasLike) {
                        false -> insertToDb(item)
                        true -> deleteFromDb(item.id)
                    }
                }
            }
        }
    }



}