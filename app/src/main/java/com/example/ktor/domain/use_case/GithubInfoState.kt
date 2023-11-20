package com.example.ktor.domain.use_case

import Github

data class GithubInfoState (
    val isLoading: Boolean  = false,
    val info: List<Github> = emptyList(),
    val error: String = ""
)