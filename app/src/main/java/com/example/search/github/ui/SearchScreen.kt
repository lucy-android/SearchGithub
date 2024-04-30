package com.example.search.github.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.search.github.R

@Composable
fun SearchScreen() {
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = stringResource(R.string.search_screen))
    }
}