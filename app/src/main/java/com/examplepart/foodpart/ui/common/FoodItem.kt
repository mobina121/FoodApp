package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.datamodels.FoodItemModel


@Composable
fun FoodItem(food: FoodItemModel) {
    Column(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 136.dp, height = 80.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 2.dp
        ) {
            Image(
                painterResource(R.drawable.pic_food), //food.imageUrl
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier.padding(
                vertical = 10.dp,
                horizontal = 10.dp
            )
        ) {

            Text(
                text = food.name,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = food.time,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}