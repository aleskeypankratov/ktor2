package com.example.ktor.domain.use_case

import Github
import com.example.ktor.common.Resource
import com.example.ktor.data.remote.dto.toGithub
import com.example.ktor.domain.repository.GithubRepository
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGithubInfoUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(): Flow<Resource<List<Github>>> = flow {
        try {
            emit(Resource.Loading())
            val info = repository.getInfo().map { it.toGithub() }
            emit(Resource.Success(info))
        } catch (e: ServerResponseException) {
            emit(Resource.Error(e.localizedMessage?: "Server didn't response"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage?: "Check internet connection"))
        }
    }
}