package com.example.shop.pages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Text(text = "Home page", modifier = modifier.fillMaxWidth())
}