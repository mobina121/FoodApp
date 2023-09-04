package com.examplepart.foodpart

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object Search : Screen("friendslist", R.string.search)
    object WhatToCook : Screen("friendslist", R.string.whatToCook)
    object Categories : Screen("friendslist", R.string.categories)
}