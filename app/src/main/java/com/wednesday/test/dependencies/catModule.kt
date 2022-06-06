package com.wednesday.test.dependencies

import com.wednesday.test.data.repository.CatRepositoryImpl
import com.wednesday.test.domain.repository.CatRepository
import org.koin.dsl.module

val catModule = module {
    //Provide a single instance of our cat repository
    single<CatRepository> { CatRepositoryImpl(get()) }
}