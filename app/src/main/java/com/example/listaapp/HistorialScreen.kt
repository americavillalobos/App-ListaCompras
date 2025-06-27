package com.example.listaapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun HistorialScreen(
    navController: NavController,
    productoViewModel: ProductoViewModel = viewModel()
) {
    val productos by productoViewModel.productosComprados.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
    ) {
        // Encabezado con flecha circular
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF0C3619), shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Historial de Compras",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF0C3619)
            )
        }

        if (productos.isEmpty()) {
            // Mensaje cuando no hay productos en el historial
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No hay productos comprados aún",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(productos) { producto ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = producto.nombre,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF0C3619)
                                )
                                Text(
                                    text = "$${producto.precio}",
                                    fontSize = 18.sp,
                                    color = Color(0xFFDE0B38),
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = { productoViewModel.volverALista(producto) },
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = Color(0xFF0C3619)
                                    )
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.reciclar_senal),
                                        contentDescription = "Volver a agregar",
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Volver a agregar", fontSize = 14.sp)
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                OutlinedButton(
                                    onClick = { productoViewModel.eliminar(producto) },
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = Color(0xFFDE0B38)
                                    ),
                                    border = BorderStroke(width = 1.dp, color = Color(0xFFDE0B38))

                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.lata_de_reciclaje),
                                        contentDescription = "Eliminar",
                                        modifier = Modifier.size(20.dp),
                                        tint = Color(0xFFDE0B38)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Eliminar", fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
