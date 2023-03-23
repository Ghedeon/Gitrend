@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("JSON_FORMAT_REDUNDANT")

package com.gitrend.data

import com.gitrend.BuildConfig
import com.gitrend.data.remote.GithubApi
import com.gitrend.domain.GithubRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun provideRepository(impl: GithubRepositoryImpl): GithubRepository

    companion object {

        @Singleton
        @Provides
        fun provideApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

        @Singleton
        @Provides
        fun providesRetrofit(
            okHttpClient: OkHttpClient,
            jsonConverter: Converter.Factory
        ): Retrofit =
            Retrofit.Builder().baseUrl(BuildConfig.API_URL)
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
        fun providesJsonConverter(): Converter.Factory {
            val contentType = "application/json".toMediaType()
            return Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
        }
    }
}