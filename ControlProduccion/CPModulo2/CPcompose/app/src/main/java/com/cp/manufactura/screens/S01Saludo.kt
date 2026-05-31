package com.cp.manufactura.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SaludoManufactura(nombre: String) {
    Text(text = "Bienvenido, $nombre!")
}

@Composable
fun S01_SaludoScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sección 1 · Sistema de Manufactura",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        SaludoManufactura("Operador")
        SaludoManufactura("Supervisor")
        SaludoManufactura("Mantenimiento")
        HorizontalDivider()
        MensajeCondicional(mostrar = true)
        MensajeCondicional(mostrar = false)
    }
}

@Composable
private fun MensajeCondicional(mostrar: Boolean) {
    if (mostrar) {
        Text("✅ Línea activa → producción en marcha")
    } else {
        Text("(mostrar = false → línea detenida)",
            color = MaterialTheme.colorScheme.outline)
    }
}

@Preview(showBackground = true)
@Composable
fun S01_Preview() {
    MaterialTheme { S01_SaludoScreen() }
}
