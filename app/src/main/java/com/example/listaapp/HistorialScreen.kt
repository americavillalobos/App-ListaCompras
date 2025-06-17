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
import androidx.navigation.NavController

@Composable
fun HistorialScreen(navController: NavController) {
    // Simulación de historial
    val historial = listOf(
        Pair("Huevo", "100.00"),
        Pair("Pan", "100.00"),
        Pair("Leche", "100.00")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Título con botón de regreso
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {/* navController.popBackStack()*/ }) {
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

        // Lista de historial
        historial.forEach { (producto, precio) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = producto, fontSize = 18.sp, color = Color.Black)
                Text(text = "$$precio", fontSize = 18.sp, color = Color(0xFFDE0B38))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones al final
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { /* acción volver a agregar */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.reciclar_senal),
                    contentDescription = "Volver a agregar",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Volver agregar", color = Color(0xFF0C3619))
            }

            OutlinedButton(onClick = { /* acción eliminar */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.lata_de_reciclaje),
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Eliminar")
            }
        }
    }
}
