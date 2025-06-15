package com.francisco.firebase.data.repository

import android.content.Context
import android.net.Uri
import com.francisco.firebase.domain.repository.Resource
import com.francisco.firebase.domain.repository.StorageRepository
import com.francisco.firebase.utils.await
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StorageRepositoryImpl(
    private val storage: FirebaseStorage
) : StorageRepository {

    override suspend fun getImageDownloadUrl(filePath: String): Resource<Uri> {
        return try {
            val fileRef = storage.reference.child(filePath)
            val downloadUrl = fileRef.downloadUrl.await()
            Resource.Success(downloadUrl)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun listImageUrlsFromPath(filePath: String): Resource<List<Uri>> {
        return try {
//            val listResult = storage.reference.child(filePath).listAll().await()
            val listResult = storage.reference.listAll().await()
            val downloadUrls = mutableListOf<Uri>()
            listResult.items.forEach { item ->
                try {
                    val url = item.downloadUrl.await()
                    downloadUrls.add(url)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            Resource.Success(downloadUrls)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun uploadAssetImage(
        assetFileName: String,
        storagePath: String,
        targetFileNameInStorage: String?,
        context: Context
    ): Resource<Uri> {
        return try {
            withContext(Dispatchers.IO) {
                context.assets.open(assetFileName).use { inputStream ->
                    val effectiveFileName = targetFileNameInStorage ?: assetFileName // Use asset name if target name not given
                    val fileRef: StorageReference = storage.reference.child(storagePath).child(effectiveFileName)

                    val uploadTask: UploadTask = fileRef.putStream(inputStream)
                    uploadTask.await()

                    if (uploadTask.isSuccessful) {
                        val downloadUrl = fileRef.downloadUrl.await()
                        Resource.Success(downloadUrl)
                    } else {
                        Resource.Error(uploadTask.exception ?: Exception("Unknown error uploading asset"))
                    }
                }
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
