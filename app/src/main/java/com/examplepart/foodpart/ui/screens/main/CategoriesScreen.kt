package com.examplepart.foodpart.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.datamodel.SubCategoryModel
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.core.AppScreens


@Composable
fun CategoriesScreen(navController: NavController) {
    val subCategoryList = listOf(
        SubCategoryModel("", "آبگوشت", "", "", false),
        SubCategoryModel("", "آبگوشت", "", "", true),
        SubCategoryModel("", "آبگوشت", "", "", false),
        SubCategoryModel("", "آبگوشت", "", "", false),
        SubCategoryModel("", "آبگوشت", "", "", false),
        SubCategoryModel("", "دلمه", "", "", false),
        SubCategoryModel("", "کوکو سبزی", "", "", false),
        SubCategoryModel("", "کباب کوبیده بزرگ", "", "", false),
        SubCategoryModel("", "زرشک پلو با مرغ", "", "", false),
        SubCategoryModel("", "باقالی پلو با ماهی", "", "", false),
        SubCategoryModel("", "خورشت قورمه", "", "", false),
        SubCategoryModel("", "بادمجان پلو", "", "", false),
        SubCategoryModel("", "کتلت", "", "", false),
        SubCategoryModel("", "آش اسفناج", "", "", false),
        SubCategoryModel("", "بزن آلو", "", "", false),
        SubCategoryModel("", "شیشلیک چلوکباب", "", "", false),
        SubCategoryModel("", "میرزاقاسمی", "", "", false),
        SubCategoryModel("", "آشرزوغنوارمه", "", "", false),
        SubCategoryModel("", "کباب تابه‌ای", "", "", false),
        SubCategoryModel("", "خورشت فسنجان", "", "", false),
    )






    Column {
        FoodPartAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.foodPart),
            showStartIcon = false,
            showEndIcon = false,
        )
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(subCategoryList) { insexSubCategory, subCategory ->
                CategoryChip(subCategory)
            }
        }






        Text("Categories Screen", fontSize = 45.sp)
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigate(AppScreens.WhatToCook.route)
            }, Modifier.weight(1f)) {
                Text("Navigate What To Cook Screen", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
//                navController.navigate(AppScreens.Profile.route)
                navController.navigate(AppScreens.Login.route)

            }, Modifier.weight(1f)) {
                Text("Navigate Profile Screen", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { navController.navigate(AppScreens.Search.route) },
                Modifier.weight(1f)
            ) {
                Text("Navigate Search Screen", fontSize = 20.sp)
            }
        }
        Button(onClick = {
            navController.navigate(AppScreens.FoodDetail.route)
        }) {
            Text(text = "Navigate Food Detail Screen", fontSize = 20.sp)
        }
    }


}


@Composable
fun CategoryChip(subCategoryModel: SubCategoryModel) {
    val borderColor = if (subCategoryModel.isSelected) MaterialTheme.colors.primary else Color.Gray
    val backgroundColor =
        if (subCategoryModel.isSelected) Color(0xA0FF6262).copy(alpha = 0.2f) else Color.Transparent

    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(64.dp)
            .clickable {

            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = backgroundColor,
                    shape = MaterialTheme.shapes.medium
                )
                .border(
                    width = if (subCategoryModel.isSelected) 1.dp else 0.dp,
                    color = borderColor,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Image(
                painter = painterResource(
                    id = if (subCategoryModel.isSelected) R.drawable.salectes_cat else R.drawable.ghaza
                ),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.Center)
            )
        }

        Text(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            text = subCategoryModel.name,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}
