package com.wednesday.test.dependencies

import com.wednesday.test.presentation.CatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    //Provide a instance of our cat viewModel
    viewModel { CatViewModel(get()) }
}