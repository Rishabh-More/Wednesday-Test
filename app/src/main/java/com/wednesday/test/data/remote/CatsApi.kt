package com.wednesday.test.data.remote

import com.wednesday.test.data.remote.dto.CatDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET("/v1/images/search")
    suspend fun getCatImages(
        @Query("limit") searchLimit: Int,
        @Query("page") page: Int,
        @Query("mime_types") mimeType: String
    ): List<CatDto>
}