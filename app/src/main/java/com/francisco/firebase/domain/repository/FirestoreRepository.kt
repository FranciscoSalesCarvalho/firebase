package com.francisco.firebase.domain.repository

import com.francisco.firebase.data.model.Produto
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

interface FirestoreRepository {

    val collection: CollectionReference?

    suspend fun getAll(): Resource<List<Produto>>
    suspend fun find(id: String): Resource<Produto?>
    suspend fun getAllRealTime(): Resource<List<Produto>>
    suspend fun add(product: Produto): Resource<DocumentReference?>
    suspend fun remove(id: String): Resource<Unit>
}
