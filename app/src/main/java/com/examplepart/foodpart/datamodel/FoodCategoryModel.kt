package com.examplepart.foodpart.datamodel

import androidx.compose.runtime.mutableStateListOf

data class FoodCategoryModel(
    val categoryId: Int,
    val categoryName: String,
    val categoryAvtarUrl: String,
    val categoryImageUrl: String,
    val subCategories: List<SubFoodCategoryModel>

)


val foodCategories = mutableStateListOf(
    FoodCategoryModel(
        categoryId = 1,
        categoryName = "ایتالیایی",
        categoryAvtarUrl = "https://example.com/italian-avatar",
        categoryImageUrl = "https://example.com/italian-image",
        subCategories = listOf(
            SubFoodCategoryModel(
                101,
                "فست فود",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = listOf(
                    FoodItemModel(
                        "102",
                        "پاستا قارچ",
                        "10",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "103",
                        "پاستامخصوص",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "104",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "105",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "106",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "107",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "108",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "109",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "110",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "111",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "112",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "113",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "114",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                )
            ),
            SubFoodCategoryModel(
                102,
                    "سوپ ها",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = emptyList()
            ),
            SubFoodCategoryModel(
                103,
                "بشقاب ها",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = listOf(
                    FoodItemModel(
                        "102",
                        "پاستا قارچ",
                        "10",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "103",
                        "پاستامخصوص",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "104",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "105",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "106",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "107",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "108",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "109",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "110",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "111",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "112",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "113",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "114",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                )
            ),
            SubFoodCategoryModel(
                104,
                "تندوری ها",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = listOf(
                    FoodItemModel(
                        "102",
                        "پاستا قارچ",
                        "10",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "103",
                        "پاستامخصوص",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "104",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "105",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "106",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "107",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "108",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "109",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "110",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "111",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "112",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "113",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "114",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                )
            ),
            SubFoodCategoryModel(
                105,
                "دسرها",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = listOf(
                    FoodItemModel(
                        "102",
                        "پاستا قارچ",
                        "10",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "103",
                        "پاستامخصوص",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "104",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "105",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "106",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "107",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "108",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "109",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "110",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "111",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "112",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "113",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "114",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                )
            ),
            SubFoodCategoryModel(
                105,
                "سالادها",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = listOf(
                    FoodItemModel(
                        "102",
                        "پاستا قارچ",
                        "10",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "103",
                        "پاستامخصوص",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "104",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "105",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "106",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "107",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "108",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "109",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "110",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "111",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "112",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "113",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                    FoodItemModel(
                        "114",
                        "پاستا",
                        "20",
                        "https://example.com/pasta-image"
                    ),
                )
            ),

            )
    ),
    FoodCategoryModel(
        categoryId = 1,
        categoryName = "مکزیکی",
        categoryAvtarUrl = "https://example.com/italian-avatar",
        categoryImageUrl = "https://example.com/italian-image",
        subCategories = listOf(
            SubFoodCategoryModel(
                101,
                "تاکوس",
                "https://example.com/pizza-avatar",
                "https://example.com/pizza-image",
                foods = listOf(
                    FoodItemModel(
                        "202",
                        "تاکو مرغ",
                        "9",
                        "https://example.com/taco-chicken-image"
                    ),
                    FoodItemModel(
                        "203",
                        "تاکو گوشت",
                        "11",
                        "https://example.com/taco-beef-image"
                    )
                )
            ),
            SubFoodCategoryModel(
                202,
                "بریتوس",
                "https://example.com/burritos-avatar",
                "https://example.com/burritos-image",
                foods = listOf(
                    FoodItemModel(
                        "204",
                        "بریتو مرغ",
                        "10",
                        "https://example.com/burrito-chicken-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            )

        )
    ),
    FoodCategoryModel(
        categoryId = 3,
        categoryName = "ترکی",
        categoryAvtarUrl = "https://example.com/mediterranean-avatar",
        categoryImageUrl = "https://example.com/mediterranean-image",
        subCategories = listOf(
            SubFoodCategoryModel(
                301,
                "سالادها",
                "https://example.com/salads-avatar",
                "https://example.com/salads-image",
                foods = listOf(
                    FoodItemModel(
                        "302",
                        "سالاد گریل",
                        "14",
                        "https://example.com/grilled-salad-image"
                    ),
                    FoodItemModel(
                        "303",
                        "سالاد سزار",
                        "12",
                        "https://example.com/caesar-salad-image"
                    ),
                    FoodItemModel(
                        "302",
                        "سالاد گریل",
                        "14",
                        "https://example.com/grilled-salad-image"
                    ),
                    FoodItemModel(
                        "303",
                        "سالاد سزار",
                        "12",
                        "https://example.com/caesar-salad-image"
                    ),
                    FoodItemModel(
                        "302",
                        "سالاد گریل",
                        "14",
                        "https://example.com/grilled-salad-image"
                    ),
                    FoodItemModel(
                        "303",
                        "سالاد سزار",
                        "12",
                        "https://example.com/caesar-salad-image"
                    ),


                    )
            ),
            SubFoodCategoryModel(
                302,
                "پیتزاها",
                "https://example.com/pizzas-avatar",
                "https://example.com/pizzas-image",
                foods = listOf(
                    FoodItemModel(
                        "304",
                        "پیتزا مارگاریتا",
                        "16",
                        "https://example.com/margherita-pizza-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                303,
                "نوشیدنی ها",
                "https://example.com/pizzas-avatar",
                "https://example.com/pizzas-image",
                foods = listOf(
                    FoodItemModel(
                        "304",
                        "پیتزا مارگاریتا",
                        "16",
                        "https://example.com/margherita-pizza-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            // Add more SubFoodCategoryModel objects as needed
        )
    ),
    FoodCategoryModel(
        categoryId = 4,
        categoryName = "هندی",
        categoryAvtarUrl = "https://example.com/indian-avatar",
        categoryImageUrl = "https://example.com/indian-image",
        subCategories = listOf(
            SubFoodCategoryModel(
                401,
                "کریها",
                "https://example.com/curries-avatar",
                "https://example.com/curries-image",
                foods = listOf(
                    FoodItemModel(
                        "402",
                        "کری مرغ",
                        "12",
                        "https://example.com/chicken-curry-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                402,
                "تندوری",
                "https://example.com/tandoori-avatar",
                "https://example.com/tandoori-image",
                foods = listOf(
                    FoodItemModel(
                        "403",
                        "تندوری مرغ",
                        "14",
                        "https://example.com/chicken-tandoori-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            // Add more SubFoodCategoryModel objects as needed
        )
    ),
    FoodCategoryModel(
        categoryId = 5,
        categoryName = "چینی",
        categoryAvtarUrl = "https://example.com/chinese-avatar",
        categoryImageUrl = "https://example.com/chinese-image",
        subCategories = listOf(
            SubFoodCategoryModel(
                501,
                "سوپ‌ها",
                "https://example.com/soups-avatar",
                "https://example.com/soups-image",
                foods = listOf(
                    FoodItemModel(
                        "502",
                        "سوپ خرچنگ",
                        "8",
                        "https://example.com/wonton-soup-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                502,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                503,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                504,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                505,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
        )
    ),
    FoodCategoryModel(
        categoryId = 6,
        categoryName = "ایرانی",
        categoryAvtarUrl = "https://example.com/chinese-avatar",
        categoryImageUrl = "https://example.com/chinese-image",
        subCategories = listOf(
            SubFoodCategoryModel(
                501,
                "سوپ‌ها",
                "https://example.com/soups-avatar",
                "https://example.com/soups-image",
                foods = listOf(
                    FoodItemModel(
                        "502",
                        "سوپ خرچنگ",
                        "8",
                        "https://example.com/wonton-soup-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                502,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                503,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                504,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
            SubFoodCategoryModel(
                505,
                "بشقاب‌ها",
                "https://example.com/dishes-avatar",
                "https://example.com/dishes-image",
                foods = listOf(
                    FoodItemModel(
                        "503",
                        "بشقاب مرغ",
                        "10",
                        "https://example.com/chicken-dish-image"
                    ),
                    // Add more FoodItemModel objects as needed
                )
            ),
        )
    )
)