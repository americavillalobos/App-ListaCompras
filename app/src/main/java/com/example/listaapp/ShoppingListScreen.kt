package com.example.listaapp

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
            .background(Color(0xFFF8F6F6))
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Encabezado con título y botón historial
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lista de compras",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0C3619),
                modifier = Modifier.padding(top = 8.dp)
            )
            IconButton(onClick = {
                navController.navigate("historial")
            }, modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.historial_de_compras),
                    contentDescription = "Historial",
                    modifier = Modifier.size(32.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar productos
        Button(
            onClick = { navController.navigate("add") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0C3619)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.agregar),
                contentDescription = "Agregar",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Agregar producto",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Lista con LazyColumn para rendimiento
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productos.value) { producto ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = producto.nombre,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF0C3619),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${producto.cantidad}x",
                            color = Color(0xFF0C3619),
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Checkbox(
                            checked = producto.comprado,
                            onCheckedChange = { checked ->
                                val actualizado = producto.copy(comprado = checked)
                                productoViewModel.actualizar(actualizado)
                            },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0C3619))
                        )
                        IconButton(
                            onClick = {
                                navController.navigate("edit/${producto.id}")
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.lapiz_de_grafito),
                                contentDescription = "Editar",
                                tint = Color(0xFF0C3619)
                            )
                        }
                        IconButton(
                            onClick = { productoViewModel.eliminar(producto) }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bote_de_basura),
                                contentDescription = "Eliminar",
                                tint = Color(0xFFDE0B38)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total
        val total = productos.value.sumOf { it.precio * it.cantidad }
        Text(
            text = "Total = $${"%.2f".format(total)}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDE0B38),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
