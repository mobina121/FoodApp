package com.examplepart.foodpart.network.categories

import com.examplepart.foodpart.database.categories.CategoryEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    val id: String,
    val name: String,
    val image: String,
    val subCategories: List<SubCategory>?
) {
    fun toCategoryEntity(): List<CategoryEntity> {
        val categoryWithSubCategoriesList = mutableListOf<CategoryEntity>()
        categoryWithSubCategoriesList.add(
            CategoryEntity(
                id = id,
                name = name,
                image = image,
                subCategories = subCategories?.map { it.id })
        )
        subCategories?.forEach {
            categoryWithSubCategoriesList.add(
                CategoryEntity(
                    id = it.id,
                    name = it.name,
                    image = it.image,
                    subCategories = listOf(),
                    isSubCategory = true
                )
            )
        }
        return categoryWithSubCategoriesList
    }
}
