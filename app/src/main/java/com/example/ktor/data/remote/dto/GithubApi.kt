package com.example.ktor.data.remote.dto

interface GithubApi {

    suspend fun getInfo(): List<GithubDto>
}