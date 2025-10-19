package com.kecc.effectivecourseschallenge.di

import com.kecc.data.repo.CoursesRepoImp
import com.kecc.effectivecourseschallenge.view_models.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    factoryOf(::CoursesRepoImp)
    viewModelOf(::MainViewModel)
}