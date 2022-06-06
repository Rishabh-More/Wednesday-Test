package com.wednesday.test.data.remote.dto


import androidx.annotation.Keep
import com.wednesday.test.domain.model.Cat

@Keep
data class CatDto(
    var breeds: List<Any>? = null,
    var categories: List<CategoryDto>? = null,
    var height: Int? = null,
    var id: String? = null,
    var url: String? = null,
    var width: Int? = null
)

/**
 * Mapper function to convert our DTO object to a Model object
 */
fun CatDto.toCat(): Cat {
    return Cat(
        breeds = this.breeds,
        categories = this.categories.toCategories(),
        height = this.height,
        id = this.id,
        url = this.url,
        width = this.width
    )
}