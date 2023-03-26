package com.gitrend.data.local

import android.content.Context
import com.charleskorn.kaml.Yaml
import com.gitrend.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import javax.inject.Inject

internal class LanguagesDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val yaml: Yaml
) {

    suspend fun getColors(): Map<String, String?> =
        withContext(Dispatchers.IO) {
            val languageMap = yaml.decodeFromStream(
                MapSerializer(String.serializer(), LanguageDto.serializer()),
                context.resources.openRawResource(R.raw.languages)
            )
            languageMap
                .filter { entry -> entry.value.color != null }
                .mapValues { entry -> entry.value.color }
        }
}