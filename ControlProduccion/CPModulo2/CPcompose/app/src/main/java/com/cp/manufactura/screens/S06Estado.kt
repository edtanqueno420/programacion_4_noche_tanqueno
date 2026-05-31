package com.cp.manufactura.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun S06_EstadoScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Sección 6 · Piezas producidas",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        DemoContadorPiezas()
        HorizontalDivider()
        DemoEstadoDerivado()
    }
}

@Composable
private fun DemoContadorPiezas() {
    var cuenta by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EtiquetaSeccion("Contador de piezas — producidas hoy")
        Text(
            text       = "$cuenta",
            style      = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { cuenta-- }) { Text("−") }
            Button(onClick = { cuenta++ }) { Text("+") }
            OutlinedButton(onClick = { cuenta = 0 }) { Text("Reiniciar") }
        }
        Text(
            "Solo el contador se recompone al producir",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DemoEstadoDerivado() {
    var nivel by remember { mutableStateOf(0) }
    val max   = 5

    val porcentaje = nivel.toFloat() / max
    val etiquetaNivel = when {
        nivel == 0    -> "Sin producción"
        nivel <= 2    -> "Bajo volumen"
        nivel <= 4    -> "Volumen medio"
        else          -> "Alto volumen"
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        EtiquetaSeccion("Rendimiento — derivado de piezas/hora")
        Text(
            "$etiquetaNivel (nivel $nivel/$max)",
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        LinearProgressIndicator(
            progress = { porcentaje },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick  = { if (nivel > 0) nivel-- },
                enabled  = nivel > 0
            ) { Text("Bajar ritmo") }
            Button(
                onClick  = { if (nivel < max) nivel++ },
                enabled  = nivel < max
            ) { Text("Subir ritmo") }
        }
        Text(
            "rendimiento = ${"%.0f".format(porcentaje * 100)}% derivado del nivel",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun S06_Preview() {
    MaterialTheme { S06_EstadoScreen() }
}
