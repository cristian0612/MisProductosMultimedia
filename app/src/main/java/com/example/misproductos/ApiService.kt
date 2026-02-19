package com.example.misproductos

import retrofit2.http.GET

interface ApiService {
    @GET("products") // El endpoint que nos dieron
    suspend fun getAllProducts(): ProductResponse
}