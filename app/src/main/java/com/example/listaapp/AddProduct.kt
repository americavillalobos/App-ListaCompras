package com.example.listaapp


import androidx.compose.foundation.layout.*
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
import com.example.listaapp.data.Producto
import com.example.listaapp.ui.theme.ProductoViewModel

import kotlinx.coroutines.launch

@Composable
fun AddScreen(
    navController: NavController,
    productoId: Int = 0,
    productoViewModel: ProductoViewModel = viewModel()

) {


    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(productoId) {
        if (productoId != 0) {
            productoViewModel.obtenerProductoPorId(productoId).collect { producto ->
                producto?.let {
                    nombre = it.nombre
                    cantidad = it.cantidad.toString()
                    precio = it.precio.toString()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        // Botón de retroceso
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = "Volver",
                    modifier = Modifier.size(48.dp),
                    tint = Color(0xFF0C3619)
                )
            }
            Text(
                text = if (productoId == 0) "Agregar Producto" else "Editar Producto",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0C3619)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campos de texto
        Text(text = "Nombre del producto", fontSize = 16.sp, color = Color.Black)
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Cantidad", fontSize = 16.sp, color = Color.Black)
        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Precio aprox", fontSize = 16.sp, color = Color.Black)
        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botones Guardar y Eliminar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                val cantidadInt = cantidad.toIntOrNull() ?: 0
                val precioDouble = precio.toDoubleOrNull() ?: 0.0
                if (nombre.isNotBlank() && cantidadInt > 0 && precioDouble > 0) {
                    val producto = Producto(
                        id = if (productoId != 0) productoId else 0,
                        nombre = nombre,
                        cantidad = cantidadInt,
                        precio = precioDouble
                    )
                    scope.launch {
                        if (productoId != 0) {
                            productoViewModel.actualizar(producto)
                        } else {
                            productoViewModel.insertar(producto)
                        }
                    }
                    navController.popBackStack()

                    } else {
                        // Aquí puedes mostrar un mensaje de error si quieres
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0C3619))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.agregar),
                    contentDescription = "Guardar",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Guardar")
            }

            OutlinedButton(
                onClick = {
                    nombre = ""
                    cantidad = ""
                    precio = ""
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lata_de_reciclaje),
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Eliminar")
            }
        }
    }
}
