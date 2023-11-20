package com.example.ktor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.ktor.domain.use_case.GithubInfoState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GithubViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val githubInfoState by viewModel.state
            GithubInfoScreen(githubInfoState = githubInfoState)
        }
    }
}

@Composable
fun GithubInfoScreen(githubInfoState: GithubInfoState) {
    Column {
        if (githubInfoState.isLoading) {
            CircularProgressIndicator()
        } else if (githubInfoState.error.isNotEmpty()) {
            Text(text = "Error: ${githubInfoState.error}")
        } else {
            githubInfoState.info.forEach { githubInfo ->
                Text(text = "ID: ${githubInfo.name}, URL: ${githubInfo.homepage}")
            }
        }
    }
}