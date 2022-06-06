package com.wednesday.test.domain.model

data class Cat(
    var breeds: List<Any>? = null,
    var categories: List<Category>? = null,
    var height: Int? = null,
    var id: String? = null,
    var url: String? = null,
    var width: Int? = null
)
