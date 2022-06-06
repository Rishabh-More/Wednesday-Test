package com.wednesday.test.domain.repository

import com.wednesday.test.domain.model.Cat

interface CatRepository {
    //function to get a list of cute cats
    suspend fun getCuteCatImages() : List<Cat>
}