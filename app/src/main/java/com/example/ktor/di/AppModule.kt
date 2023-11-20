package com.example.ktor.di;

import Github
import androidx.compose.ui.unit.Constraints
import com.example.ktor.common.Constants
import com.example.ktor.data.remote.dto.GithubApi
import com.example.ktor.data.repository.GithubRepositoryImpl
import com.example.ktor.domain.repository.GithubRepository
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    suspend fun provideGithubApi(): Github {
        val client = HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        return client.get(Constants.BASE_URL).body<Github>()
    }

    @Provides
    @Singleton
    fun provideGithubRepository(api: GithubApi) :GithubRepository {
        return GithubRepositoryImpl(api)
    }
}
