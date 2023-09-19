package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.examplepart.foodpart.R
import com.examplepart.foodpart.database.food.FoodEntity


@Composable
fun FoodItem(
    modifier: Modifier,
    food: FoodEntity,
    onClick: (id: String) -> Unit
) {

    val readyTime = food.readyTime ?: 0
    val cookTime = food.cookTime ?: 0
    val totalTime = (readyTime + cookTime).toString()

    val formattedCookTime = stringResource(R.string.cookTime, totalTime)


    Column(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick(food.id)
            },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 136.dp, height = 80.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 2.dp
        ) {
            AsyncImage(
                model = food.image,
                contentDescription = food.name,
                error = painterResource(id = R.drawable.logo_dark_1),
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
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                text = formattedCookTime,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}


