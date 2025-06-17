package com.example.listaapp

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Lista(navController: NavController) {
    val items = remember {
        mutableStateListOf(
            ShoppingItem("Queso"),
            ShoppingItem("Pan"),
            ShoppingItem("Leche")
        )
    }

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

        // Lista de productos
        Column(modifier = Modifier.weight(1f)) {
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "1x", color = Color.Black)
                    Checkbox(
                        checked = item.checked,
                        onCheckedChange = { item.checked = it }
                    )
                    IconButton(onClick = { /* editar */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.lapiz_de_grafito),
                            contentDescription = "Editar"
                        )
                    }
                    IconButton(onClick = { items.remove(item) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bote_de_basura),
                            contentDescription = "Eliminar"
                        )
                    }
                }
            }
        }

        // Total
        Text(
            text = "Total  = $",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFDE0B38),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

data class ShoppingItem(
    val name: String,
    var checked: Boolean = false
)
