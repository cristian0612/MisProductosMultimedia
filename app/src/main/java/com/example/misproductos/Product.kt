package com.example.misproductos

// Este es el objeto que representa a un solo producto
data class Product(
    val _id: String,
    val name: String,
    val description: String,
    val price: Double,
    val image: String
)

// Esta es la respuesta global que envía la API (queremos el array 'results')
data class ProductResponse(
    val results: List<Product>
)
