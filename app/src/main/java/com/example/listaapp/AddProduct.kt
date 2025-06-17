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
import androidx.navigation.NavController

@Composable
fun AddScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

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
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = "Agregar Producto",
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
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Precio aprox", fontSize = 16.sp, color = Color.Black)
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth()
        )



        Spacer(modifier = Modifier.height(32.dp))

        // Botones Guardar y Eliminar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* acción guardar */ },
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
                onClick = { /* acción eliminar */ }
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
