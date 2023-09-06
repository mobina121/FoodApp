package com.examplepart.foodpart.datamodel

import androidx.compose.runtime.mutableStateListOf

data class FoodCategoryModel(
    val foodId: Int,
    val foodName: String,
    val foodAvtarUrl: String,
    val foodImageUrl: String,
    val subCategories: List<SubFoodCategoryModel>

)


val foodCategories = mutableStateListOf<FoodCategoryModel>(
    FoodCategoryModel(
        foodId = 1,
        foodName = "ایتالیایی",
        foodAvtarUrl = "https://example.com/italian-avatar",
        foodImageUrl = "https://example.com/italian-image",
        subCategories = listOf(
            SubFoodCategoryModel(101, "پیتزا پپرونی", "https://example.com/pizza-avatar", "https://example.com/pizza-image"),
            SubFoodCategoryModel(102, "پاستا قارچ", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(103, "پاستامخصوص", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(104, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(105, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(106, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(107, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(108, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(109, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(110, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(111, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(112, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(113, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
            SubFoodCategoryModel(114, "پاستا", "https://example.com/pasta-avatar", "https://example.com/pasta-image"),
        )
    ),
    FoodCategoryModel(
        foodId = 2,
        foodName = "آسیایی",
        foodAvtarUrl = "https://example.com/asian-avatar",
        foodImageUrl = "https://example.com/asian-image",
        subCategories = listOf(
            SubFoodCategoryModel(201, "سوشی", "https://example.com/sushi-avatar", "https://example.com/sushi-image"),
            SubFoodCategoryModel(202, "نودلز", "https://example.com/noodles-avatar", "https://example.com/noodles-image")
        )
    ),
    FoodCategoryModel(
        foodId = 3,
        foodName = "آمریکایی",
        foodAvtarUrl = "https://example.com/american-avatar",
        foodImageUrl = "https://example.com/american-image",
        subCategories = emptyList()
    ),
    FoodCategoryModel(
        foodId = 4,
        foodName = "مکزیکی",
        foodAvtarUrl = "https://example.com/mexican-avatar",
        foodImageUrl = "https://example.com/mexican-image",
        subCategories = listOf(
            SubFoodCategoryModel(401, "تاکوس", "https://example.com/tacos-avatar", "https://example.com/tacos-image"),
            SubFoodCategoryModel(402, "بریتوس", "https://example.com/burritos-avatar", "https://example.com/burritos-image")
        )
    ),
    FoodCategoryModel(
        foodId = 5,
        foodName = "مناطق مدیترانه",
        foodAvtarUrl = "https://example.com/mediterranean-avatar",
        foodImageUrl = "https://example.com/mediterranean-image",
        subCategories = emptyList()
    ),
    FoodCategoryModel(
        foodId = 6,
        foodName = "هندی",
        foodAvtarUrl = "https://example.com/indian-avatar",
        foodImageUrl = "https://example.com/indian-image",
        subCategories = listOf(
            SubFoodCategoryModel(601, "کریها", "https://example.com/curries-avatar", "https://example.com/curries-image"),
            SubFoodCategoryModel(602, "تندوری", "https://example.com/tandoori-avatar", "https://example.com/tandoori-image")
        )
    ),
    FoodCategoryModel(
        foodId = 7,
        foodName = "چینی",
        foodAvtarUrl = "https://example.com/chinese-avatar",
        foodImageUrl = "https://example.com/chinese-image",
        subCategories = emptyList()
    ),
    FoodCategoryModel(
        foodId = 8,
        foodName = "ژاپنی",
        foodAvtarUrl = "https://example.com/japanese-avatar",
        foodImageUrl = "https://example.com/japanese-image",
        subCategories = listOf(
            SubFoodCategoryModel(801, "سوشی", "https://example.com/japanese-sushi-avatar", "https://example.com/japanese-sushi-image")
        )
    ),
    FoodCategoryModel(
        foodId = 9,
        foodName = "تایلندی",
        foodAvtarUrl = "https://example.com/thai-avatar",
        foodImageUrl = "https://example.com/thai-image",
        subCategories = emptyList()
    ),
    FoodCategoryModel(
        foodId = 10,
        foodName = "گیاهی",
        foodAvtarUrl = "https://example.com/vegetarian-avatar",
        foodImageUrl = "https://example.com/vegetarian-image",
        subCategories = emptyList()
    )
)




