package com.example.listaapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.listaapp.ui.theme.ProductoViewModel

@Composable
fun HistorialScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel = viewModel()
) {
    val productos by productoViewModel.productosComprados.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Encabezado
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = "Volver",
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = "Historial",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF0C3619),
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de productos comprados
        LazyColumn {
            items(productos) { producto ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = producto.nombre, fontSize = 18.sp)
                        Text(text = "$${producto.precio}", fontSize = 18.sp, color = Color(0xFFDE0B38))
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TextButton(onClick = { productoViewModel.volverALista(producto) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.reciclar_senal),
                                contentDescription = "Volver a agregar",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Volver agregar", color = Color(0xFF0C3619))
                        }

                        OutlinedButton(onClick = { productoViewModel.eliminar(producto) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.lata_de_reciclaje),
                                contentDescription = "Eliminar",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Eliminar")
                        }
                    }

                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
