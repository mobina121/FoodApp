package com.examplepart.foodpart.ui.screens.whatotcook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.core.AppScreens

@Composable
fun WhatToCookScreen(navController: NavController) {

    var whatDoYouHaveText by remember { mutableStateOf("") }
    var howMuchTimeDoYouHave by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("Option1") }

    var noMatter = stringResource(id = R.string.noMatter)
    var easy = stringResource(id = R.string.easy)
    var normal = stringResource(id = R.string.normal)
    var difficult = stringResource(id = R.string.difficult)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        FoodPartAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = stringResource(id = R.string.whatToCook),
            showStartIcon = false,
            showEndIcon = false,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.surface)
                .padding(vertical = 20.dp, horizontal = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.help),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle1
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "arrow forward icon",
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.helpText),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(56.dp),
            value = whatDoYouHaveText,
            onValueChange = { text -> whatDoYouHaveText = text },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                unfocusedBorderColor = MaterialTheme.colors.surface,
                focusedBorderColor = MaterialTheme.colors.onSurface,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    text = stringResource(R.string.whatDoYouHave),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = stringResource(R.string.helpOne),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface
        )

        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.surface),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            OutlinedTextField(
                modifier = Modifier.weight(4f),
                value = howMuchTimeDoYouHave,
                onValueChange = { text -> howMuchTimeDoYouHave = text },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    unfocusedBorderColor = MaterialTheme.colors.surface,
                    focusedBorderColor = MaterialTheme.colors.onSurface,
                ),
                shape = MaterialTheme.shapes.medium,
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        text = stringResource(R.string.howMuchTimeDoYouHave),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface
                    )
                },
                textStyle = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.time),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = stringResource(R.string.howDifficultIsTheOrder),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface
        )

        CustomRadioButtonGroup()

        Row(


            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            RadioButton(
                selected = selectedOption.value == noMatter,
                onClick = { selectedOption.value = noMatter }
            )
            Text(stringResource(id = R.string.noMatter), style = MaterialTheme.typography.caption)
            RadioButton(
                selected = selectedOption.value == easy,
                onClick = { selectedOption.value = easy }
            )
            Text(stringResource(id = R.string.easy), style = MaterialTheme.typography.caption)
            RadioButton(
                selected = selectedOption.value == normal,
                onClick = { selectedOption.value = normal }
            )
            Text(stringResource(id = R.string.normal), style = MaterialTheme.typography.caption)
            RadioButton(
                selected = selectedOption.value == difficult,
                onClick = { selectedOption.value = difficult }
            )
            Text(stringResource(id = R.string.difficult), style = MaterialTheme.typography.caption)


        }


    }




}
enum class DifficultyOption(val title: String, val icon: ImageVector) {
    NO_MATTER("No Matter", Icons.Default.Check),
    EASY("Easy", Icons.Default.Star),
    NORMAL("Normal", Icons.Default.Info),
    DIFFICULT("Difficult", Icons.Default.Warning)
}
@Composable
fun CustomRadioButtonGroup() {
    var selectedOption by remember { mutableStateOf(DifficultyOption.NO_MATTER) }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DifficultyOption.values().forEach { option ->
            RadioButton(
                selected = selectedOption == option,
                onClick = { selectedOption = option },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = option.title, style = MaterialTheme.typography.caption)
        }
    }
}








