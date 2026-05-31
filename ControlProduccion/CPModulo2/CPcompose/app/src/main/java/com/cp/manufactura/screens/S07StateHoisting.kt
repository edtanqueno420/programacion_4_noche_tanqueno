package com.cp.manufactura.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun S07_StateHoistingScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Sección 7 · Selección de máquina",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        DemoEstadoAtrapado()
        HorizontalDivider()
        DemoEstadoElevado()
    }
}

@Composable
private fun DemoEstadoAtrapado() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        EtiquetaSeccion("❌ Estado atrapado — sin supervisión")
        Text(
            "El estado vive dentro del botón. El supervisor no sabe cuántas veces se usó.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        BotonAtrapado()
        Text(
            "El supervisor no puede leer el conteo ❌",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun BotonAtrapado() {
    var cuenta by remember { mutableStateOf(0) }
    Button(onClick = { cuenta++ }) {
        Text("Usado $cuenta veces (estado atrapado)")
    }
}

@Composable
private fun DemoEstadoElevado() {
    var seleccion by remember { mutableStateOf<String?>(null) }
    var historial by remember { mutableStateOf(listOf<String>()) }

    val opciones = listOf("Máquina A: Corte", "Máquina B: Soldadura", "Máquina C: Ensamble", "Máquina D: Pintura")

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        EtiquetaSeccion("✅ Estado elevado — supervisor coordina")
        Text(
            "La máquina solo notifica su estado. El supervisor actualiza selección e historial.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SelectorOpciones(
            opciones   = opciones,
            seleccion  = seleccion,
            onSeleccion = { opcion ->
                seleccion = opcion
                historial = (historial + opcion).takeLast(4)
            }
        )
        seleccion?.let { sel ->
            val color = when {
                "Corte"     in sel -> Color(0xFFFFCDD2)
                "Soldadura"    in sel -> Color(0xFFC8E6C9)
                "Ensamble"     in sel -> Color(0xFFBBDEFB)
                "Pintura" in sel -> Color(0xFFFFF9C4)
                else              -> Color.Transparent
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Text("Seleccionada: $sel",
                    style = MaterialTheme.typography.labelLarge)
            }
        }
        if (historial.isNotEmpty()) {
            Text(
                "Historial: ${historial.joinToString(" → ")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SelectorOpciones(
    opciones:    List<String>,
    seleccion:   String?,
    onSeleccion: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        opciones.forEach { opcion ->
            val estaSeleccionado = seleccion == opcion
            Button(
                onClick  = { onSeleccion(opcion) },
                modifier = Modifier.fillMaxWidth(),
                colors   = if (estaSeleccionado)
                    ButtonDefaults.buttonColors()
                else
                    ButtonDefaults.outlinedButtonColors()
            ) {
                Text(opcion)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S07_Preview() {
    MaterialTheme { S07_StateHoistingScreen() }
}
