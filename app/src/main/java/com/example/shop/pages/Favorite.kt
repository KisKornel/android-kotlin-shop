package com.example.shop.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoritePage(modifier: Modifier = Modifier) {
    Text(text = "Favorite page", modifier = modifier.fillMaxWidth())
}