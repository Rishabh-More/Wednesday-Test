package com.wednesday.test.common

sealed class Response<T>(val data: T?, val message: String? = null) {
    class Success<T>(data: T): Response<T>(data)
    class Error<T>(data: T? = null, message: String?): Response<T>(data, message)
    class Loading<T>() : Response<T>(null, null)
}