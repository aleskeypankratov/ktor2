package com.example.ktor.presentation.components

import Github
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun InfoList(github: Github) {

    Row(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Text(
            text = "${github.login.toString()} " +
                    "${github.homepage.toString()}" +
                    "${github.createdAt.toString()}" +
                    "${github.language.toString()}"
        )
    }
}