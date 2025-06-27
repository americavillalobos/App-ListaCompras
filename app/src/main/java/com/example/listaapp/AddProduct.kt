package com.example.listaapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .background(Color(0xFFF0F0F0))
            .padding(horizontal = 20.dp, vertical = 30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF0C3619), shape = RoundedCornerShape(12.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flecha),
                    contentDescription = "Volver",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = if (productoId == 0) "Agregar Producto" else "Editar Producto",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp,
                color = Color(0xFF0C3619)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(text = "Nombre del producto", fontSize = 20.sp, color = Color(0xFF0C3619))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nuevoTexto ->
                    if (nuevoTexto.all { it.isLetterOrDigit() || it.isWhitespace() }) {
                        nombre = nuevoTexto
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                singleLine = true
            )

            Text(text = "Cantidad", fontSize = 20.sp, color = Color(0xFF0C3619))
            OutlinedTextField(
                value = cantidad,
                onValueChange = { nuevoTexto ->
                    if (nuevoTexto.isEmpty() || nuevoTexto.all { it.isDigit() }) {
                        cantidad = nuevoTexto
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                singleLine = true
            )

            Text(text = "Precio aprox", fontSize = 20.sp, color = Color(0xFF0C3619))
            OutlinedTextField(
                value = precio,
                onValueChange = { nuevoTexto ->
                    if (nuevoTexto.isEmpty() || nuevoTexto.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        precio = nuevoTexto
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
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

                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0C3619)),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.agregar),
                    contentDescription = "Guardar",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Guardar", color = Color.White, fontSize = 18.sp)
            }

            OutlinedButton(
                onClick = {
                    nombre = ""
                    cantidad = ""
                    precio = ""
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lata_de_reciclaje),
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF0C3619)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Eliminar", color = Color(0xFF0C3619), fontSize = 18.sp)
            }
        }
    }
}
