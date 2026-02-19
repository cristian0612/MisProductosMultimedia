package com.example.misproductos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
//Lógica de negocio y conexión Retrofit.
    var productList = mutableStateOf<List<Product>>(emptyList())
        private set

    init {
        getProducts()
    }
//**********
    private fun getProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getAllProducts()
                productList.value = response.results
            } catch (e: Exception) {

            }
        }
    }
}