package com.example.ktor.domain.repository

import com.example.ktor.data.remote.dto.GithubDto

interface GithubRepository {

    suspend fun getInfo(): List<GithubDto>
}