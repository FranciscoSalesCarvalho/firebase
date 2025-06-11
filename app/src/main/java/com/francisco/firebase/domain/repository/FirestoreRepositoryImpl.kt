package com.francisco.firebase.domain.repository

import com.francisco.firebase.data.model.Produto
import com.francisco.firebase.utils.await
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class FirestoreRepositoryImpl(
    private val firestore: FirebaseFirestore
) : FirestoreRepository {
    override val collection: CollectionReference?
        get() = firestore.collection("produtos")

    override suspend fun getAll(): Resource<List<Produto>> {
        val result = collection?.get()?.await()
        val products = mutableListOf<Produto>()
        for (document in result?.documents!!) {
            document.toObject<Produto>()?.let { prod: Produto ->
                products.add(prod)
            }
        }
        return Resource.Success(products)
    }

    override suspend fun find(id: String): Resource<Produto?> {
        val document = collection?.document(id)?.get()?.await()
        val result = document?.toObject<Produto>()
        return Resource.Success(result)
    }

    override suspend fun getAllRealTime(): Resource<List<Produto>> {
        val produtos = mutableListOf<Produto>()
        collection?.addSnapshotListener { value, error ->
            value?.let {
                for (documento in it.documents) {
                    documento.toObject<Produto>()?.let { pro ->
                        produtos.add(pro)
                    }
                }
            }
        }
        return Resource.Success(produtos)
    }

    override suspend fun add(product: Produto): Resource<DocumentReference?> {
        val produtoMapeado = mapOf(
            "nome" to product.nome,
            "preco" to product.preco,
        )
        val result = collection?.add(produtoMapeado)?.await()
        return Resource.Success(result)
    }

    override suspend fun remove(id: String): Resource<Unit> {
        collection?.document(id)?.delete()?.await()
        return Resource.Success(Unit)
    }
}
