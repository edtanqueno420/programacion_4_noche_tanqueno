package com.cp.manufactura.material

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Paso01SumaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Cálculo de costo de producción",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        HorizontalDivider()
        CalcularCosto()
    }
}

@Composable
private fun CalcularCosto() {
    var producto    by remember { mutableStateOf("") }
    var cantidad    by remember { mutableStateOf("0") }
    var precio  by remember { mutableStateOf("0") }

    var subtotal    by remember { mutableStateOf("0") }
    var descuento    by remember { mutableStateOf("0") }
    var total  by remember { mutableStateOf("0") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Costo de producción",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary)

        OutlinedTextField(
            value           = producto,
            onValueChange   = { producto = it },
            label           = { Text("Nombre de la pieza") },
            leadingIcon     = { Icon(Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine      = true,
            modifier        = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value           = cantidad,
            onValueChange   = { cantidad = it },
            label           = { Text("Cantidad a producir") },
            leadingIcon     = { Icon(Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine      = true,
            modifier        = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value           = precio,
            onValueChange   = { precio = it },
            label           = { Text("Costo unitario") },
            leadingIcon     = { Icon(Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine      = true,
            modifier        = Modifier.fillMaxWidth()
        )

        Button(
            onClick  = {
                val cantidadDouble = cantidad.toDoubleOrNull()?:0.0
                val precioDouble = precio.toDoubleOrNull()?:0.0
                val subtotalCalculado = cantidadDouble * precioDouble
                var descuentoCalculado = 0.0

                if (subtotalCalculado > 1000) {
                    descuentoCalculado = subtotalCalculado * 0.15
                } else if (subtotalCalculado >= 500) {
                    descuentoCalculado = subtotalCalculado * 0.10
                } else if (subtotalCalculado >= 100) {
                    descuentoCalculado = subtotalCalculado * 0.05
                }

                val totalCalculado = subtotalCalculado - descuentoCalculado

                subtotal = subtotalCalculado.toString()
                descuento = descuentoCalculado.toString()
                total = totalCalculado.toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="Calcular costo")
        }
        Text(text = "Pieza: $producto")
        Text(text = "Subtotal: $subtotal")
        Text(text = "Descuento por volumen: $descuento")
        Text(text = "Total: $total")
    }
}
@Preview(showBackground = true)
@Composable
fun Paso01SumaPreview() {
    MaterialTheme {
        Paso01SumaScreen()
    }
}
