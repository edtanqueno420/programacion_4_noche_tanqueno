package com.cp.compose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CP_Saludo(nombre: String) {
    Text(text = "Hola, $nombre!")
}

@Composable
fun CP_S01_SaludoScreen() {
    Column(
        modifier            = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Seccion 1 - @Composable basico",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()

        CP_Saludo("Planta")
        CP_Saludo("Produccion")
        CP_Saludo("Manufactura")

        HorizontalDivider()

        CP_MensajeCondicional(mostrar = true)
        CP_MensajeCondicional(mostrar = false)
    }
}

@Composable
private fun CP_MensajeCondicional(mostrar: Boolean) {
    if (mostrar) {
        Text("mostrar = true  -> se dibuja")
    } else {
        Text("(mostrar = false -> este Text existe en codigo pero no se dibuja)",
            color = MaterialTheme.colorScheme.outline)
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S01_Preview() {
    MaterialTheme { CP_S01_SaludoScreen() }
}
