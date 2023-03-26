package com.gitrend.di

import com.gitrend.data.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
internal class MockNetworkModule : NetworkModule() {

    override var baseUrl: HttpUrl = "https://localhost:8080".toHttpUrl()
}