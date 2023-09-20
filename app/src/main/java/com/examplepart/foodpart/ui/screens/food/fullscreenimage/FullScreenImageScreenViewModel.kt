package com.examplepart.foodpart.ui.screens.food.fullscreenimage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullScreenImageScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val imageId: String = savedStateHandle.get<String>("id").orEmpty()
}
