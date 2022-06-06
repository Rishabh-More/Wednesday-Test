package com.wednesday.test.data.repository

import com.wednesday.test.data.remote.CatsApi
import com.wednesday.test.data.remote.dto.toCat
import com.wednesday.test.domain.model.Cat
import com.wednesday.test.domain.repository.CatRepository

class CatRepositoryImpl(private val api: CatsApi) : CatRepository {

    override suspend fun getCuteCatImages(): List<Cat> {
        //Return a list of cats from our api
        return api.getCatImages(
            searchLimit = 20,
            page = 1,
            mimeType = "png"
        ).map { it.toCat() }
    }
}