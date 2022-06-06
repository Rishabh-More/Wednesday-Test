package com.wednesday.test.data.remote.dto

import androidx.annotation.Keep
import com.wednesday.test.domain.model.Category

@Keep
data class CategoryDto(
    var id: Int = 0,
    var name: String? = null
)

/**
 * Mapper function to convert a DTO object to a Model Object
 */
fun CategoryDto.toCategory(): Category {
    return Category(id, name)
}

/**
 * Mapper function to return a List<Category> from a List<CategoryDto>
 */
fun List<CategoryDto>?.toCategories(): List<Category> {
    return this?.map { it.toCategory() } ?: emptyList()
}