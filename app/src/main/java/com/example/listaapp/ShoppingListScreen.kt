package com.example.listaapp

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.listaapp.ui.theme.ProductoViewModel
import com.example.listaapp.ui.theme.ProductoViewModelFactory

@Composable
fun Lista(navController: NavController) {
    val context = LocalContext.current.applicationContext as Application
    val productoViewModel: ProductoViewModel = viewModel(
        factory = ProductoViewModelFactory(context)
    )
    val productos = productoViewModel.productos.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF8F6F6)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lista de compras",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0C3619)
            )
            IconButton(onClick = {
                navController.navigate("historial")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.historial_de_compras),
                    contentDescription = "Historial"
                )
            }
        }

        // Botón de agregar
        Button(
            onClick = {
                navController.navigate("add")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0C3619)),
            shape = RoundedCornerShape(50)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.agregar),
                contentDescription = "Agregar"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Lista de productos reales
        Column(modifier = Modifier.weight(1f)) {
            productos.value.forEach { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = producto.nombre,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "${producto.cantidad}x", color = Color.Black)

                    // ✅ Checkbox funcional para marcar como comprado
                    Checkbox(
                        checked = producto.comprado,
                        onCheckedChange = { checked ->
                            // Al marcarlo, se actualiza y se ocultará de la lista
                            val actualizado = producto.copy(comprado = checked)
                            productoViewModel.actualizar(actualizado)
                        }
                    )

                    // Botón editar
                    IconButton(onClick = {
                        navController.navigate("edit/${producto.id}")

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.lapiz_de_grafito),
                            contentDescription = "Editar"
                        )
                    }

                    // Botón eliminar
                    IconButton(onClick = {
                        productoViewModel.eliminar(producto)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bote_de_basura),
                            contentDescription = "Eliminar"
                        )
                    }
                }
            }
        }

        // Total
        val total = productos.value.sumOf { it.precio * it.cantidad }
        Text(
            text = "Total = $${"%.2f".format(total)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDE0B38),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

