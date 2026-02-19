package com.example.misproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicamos un contenedor básico de Material Design 3
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ProductListScreen()
            }
        }
    }
}

@Composable
fun ProductListScreen(viewModel: ProductViewModel = viewModel()) {
    // Obtenemos la lista desde el ViewModel
    val products = viewModel.productList.value

    Column(modifier = Modifier.fillMaxSize()) {
        // Título de la App
        Text(
            text = "Catálogo de Productos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )

        if (products.isEmpty()) {
            // Si aún no hay datos, mostramos un indicador de carga
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Listado con scroll automático
            LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(products) { product ->
                    ProductItem(product = product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto desde la URL de la API
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "${product.price}€",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF2E7D32), // Color verde para el precio
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 2
                )
            }
        }
    }
}