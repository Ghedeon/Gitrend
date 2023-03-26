@file:Suppress("JSON_FORMAT_REDUNDANT")
@file:OptIn(ExperimentalSerializationApi::class)

package com.gitrend.data.di

import com.charleskorn.kaml.Yaml
import com.gitrend.BuildConfig
import com.gitrend.data.remote.GithubApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal open class NetworkModule {

    open var baseUrl: HttpUrl = BuildConfig.API_URL.toHttpUrl()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        jsonConverter: Converter.Factory
    ): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun provideJsonConverter(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    fun provideYaml(): Yaml =
        Yaml(configuration = Yaml.default.configuration.copy(strictMode = false))
}