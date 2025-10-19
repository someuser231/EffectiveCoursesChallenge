package com.kecc.effectivecourseschallenge.di

import com.kecc.data.local.MainDb
import com.kecc.data.repo.CoursesRepoImp
import com.kecc.effectivecourseschallenge.view_models.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainDb.getDb(get())
    }
    factoryOf(::CoursesRepoImp)
    viewModelOf(::MainViewModel)
}