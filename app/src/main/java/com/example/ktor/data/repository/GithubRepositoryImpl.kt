package com.example.ktor.data.repository

import com.example.ktor.data.remote.dto.GithubApi
import com.example.ktor.data.remote.dto.GithubDto
import com.example.ktor.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {
    override suspend fun getInfo(): List<GithubDto> {
        return api.getInfo()
    }
}