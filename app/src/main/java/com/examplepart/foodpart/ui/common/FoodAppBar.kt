//    TopAppBar(
//        modifier = modifier,
//        backgroundColor = backgroundColor,
//        elevation = elevation,
//        title = title,
//        actions = actions,
//        navigationIcon = navigationIcon
////        elevation = 0.dp,
////        backgroundColor = MaterialTheme.colors.background,
////        content = {
////            Row(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(vertical = 10.dp, horizontal = 30.dp),
////                verticalAlignment = Alignment.CenterVertically,
////                horizontalArrangement = Arrangement.SpaceBetween
////            ) {
////                Row(
////                    verticalAlignment = Alignment.CenterVertically,
////                    horizontalArrangement = Arrangement.Center
////                ) {
////                    Icon(
////                        imageVector = Icons.Default.KeyboardArrowRight,
////                        contentDescription = "Back Icon",
////                        tint = MaterialTheme.colors.onBackground
////                    )
////                    Spacer(
////                        modifier = Modifier.size(10.dp)
////                    )
////
////                    Text(
////                        text = stringResource(R.string.food_details),
////                        style = MaterialTheme.typography.h2,
////                        color = MaterialTheme.colors.onBackground,
////                        maxLines = 1,
////                        overflow = TextOverflow.Ellipsis,
////                    )
////                }
////                Spacer(modifier = Modifier.weight(1f))
////                IconButton(onClick = {}) {
////                    Icon(
////                        painter = painterResource(id = R.drawable.more_horiz),
////                        contentDescription = "Person Icon",
////                        tint = MaterialTheme.colors.onBackground
////                    )
////                }
////
////            }
////        }
//    )
//}
//


//////////////////////////////////////////////////////
package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FoodAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 0.dp
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = elevation,
        title = title,
        actions = actions,
        navigationIcon = navigationIcon
    )
}

@Composable
fun FoodAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    content: @Composable RowScope.() -> Unit
) {
    TopAppBar(
        modifier = modifier,
        elevation = elevation,
        backgroundColor = backgroundColor,
        contentPadding = contentPadding,
        content = content
    )
}
