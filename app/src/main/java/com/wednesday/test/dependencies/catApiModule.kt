package com.wednesday.test.dependencies

import com.wednesday.test.data.remote.CatsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val catApiModule = module {
    //Provide a single instance of our cat api with retrofit
    single<CatsApi> { get<Retrofit>().create(CatsApi::class.java) }
}