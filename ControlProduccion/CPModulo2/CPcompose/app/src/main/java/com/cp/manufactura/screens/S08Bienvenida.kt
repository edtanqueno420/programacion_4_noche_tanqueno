package com.cp.manufactura.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun S08_BienvenidaScreen() {
    var paso by remember { mutableStateOf(1) }

    Column(
        modifier            = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (paso) {
            1 -> PasoUno(onSiguiente = { paso = 2 })
            2 -> PasoDos(onSiguiente = { paso = 3 }, onVolver = { paso = 1 })
            3 -> PasoTres(onReiniciar = { paso = 1 })
        }
    }
}

@Composable
private fun PasoUno(onSiguiente: () -> Unit) {
    var sectorElegido by remember { mutableStateOf<String?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IndicadorPasos(pasoActual = 1, totalPasos = 3)
        Spacer(Modifier.height(8.dp))
        Text("Elige un sector",
            style      = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        Text("Selecciona el sector de producción",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.height(8.dp))
        listOf("🔵 Mecanizado", "🟢 Ensamble", "🟣 Soldadura").forEach { sector ->
            val seleccionado = sectorElegido == sector
            Button(
                onClick  = { sectorElegido = sector },
                modifier = Modifier.fillMaxWidth(),
                colors   = if (seleccionado) ButtonDefaults.buttonColors()
                else ButtonDefaults.outlinedButtonColors()
            ) {
                Text(sector)
                if (seleccionado) {
                    Spacer(Modifier.width(8.dp))
                    Text("✓")
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(
            onClick  = onSiguiente,
            enabled  = sectorElegido != null,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape    = RoundedCornerShape(12.dp)
        ) {
            Text("Siguiente →")
        }
    }
}

@Composable
private fun PasoDos(onSiguiente: () -> Unit, onVolver: () -> Unit) {
    var nivel by remember { mutableStateOf(1) }
    val max = 5

    val descripcion = when (nivel) {
        1    -> "Planta nueva"
        2    -> "Producción básica"
        3    -> "Volumen medio"
        4    -> "Alta producción"
        else -> "Producción continua"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IndicadorPasos(pasoActual = 2, totalPasos = 3)
        Spacer(Modifier.height(8.dp))
        Text("Capacidad de producción",
            style      = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        Text(
            "Nivel $nivel de $max",
            style      = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.primary
        )
        Text(descripcion,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        LinearProgressIndicator(
            progress = { nivel.toFloat() / max },
            modifier = Modifier.fillMaxWidth().height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick  = { if (nivel > 1) nivel-- },
                enabled  = nivel > 1
            ) { Text("−") }
            Button(
                onClick  = { if (nivel < max) nivel++ },
                enabled  = nivel < max
            ) { Text("+") }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = onVolver, modifier = Modifier.weight(1f)) {
                Text("← Volver")
            }
            Button(onClick = onSiguiente, modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)) {
                Text("Siguiente →")
            }
        }
    }
}

@Composable
private fun PasoTres(onReiniciar: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        IndicadorPasos(pasoActual = 3, totalPasos = 3)
        Spacer(Modifier.height(8.dp))
        Box(
            modifier         = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("✓",
                style      = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color      = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        Text("¡Planta configurada!",
            style      = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
        Text(
            "Has configurado los parámetros iniciales.\nRevisa las órdenes de trabajo para empezar.",
            style     = MaterialTheme.typography.bodyMedium,
            color     = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        OutlinedButton(
            onClick  = onReiniciar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("↺ Configurar de nuevo")
        }
    }
}

@Composable
private fun IndicadorPasos(pasoActual: Int, totalPasos: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        (1..totalPasos).forEach { paso ->
            Box(
                modifier = Modifier
                    .size(if (paso == pasoActual) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(
                        if (paso <= pasoActual) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S08_Preview() {
    MaterialTheme { S08_BienvenidaScreen() }
}
