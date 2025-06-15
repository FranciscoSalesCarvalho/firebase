package com.francisco.firebase.domain.repository

import android.content.Context
import android.net.Uri

interface StorageRepository {
    suspend fun getImageDownloadUrl(filePath: String): Resource<Uri>
    suspend fun listImageUrlsFromPath(filePath: String): Resource<List<Uri>>
    suspend fun uploadAssetImage(
        assetFileName: String,
        storagePath: String,
        targetFileNameInStorage: String? = null,
        context: Context // Pass Android Context to access assets
    ): Resource<Uri>
}