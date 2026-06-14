package com.cp.manufactura.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun S03_ButtonScreen() {
    var ultimoClick by remember { mutableStateOf("(ninguno)") }

    Column(
        modifier            = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sección 3 · Acciones de producción",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        Surface(
            color    = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text     = "Última acción: $ultimoClick",
                modifier = Modifier.padding(12.dp),
                style    = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.height(4.dp))
        Button(
            onClick  = { ultimoClick = "Iniciar producción" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Iniciar producción") }
        Button(
            onClick  = { ultimoClick = "Agregar pieza a orden" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector        = Icons.Default.Add,
                contentDescription = null,
                modifier           = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Agregar pieza")
        }
        OutlinedButton(
            onClick  = { ultimoClick = "Pausar línea" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Pausar línea") }
        TextButton(
            onClick  = { ultimoClick = "Ver reporte" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Ver reporte") }
        ElevatedButton(
            onClick  = { ultimoClick = "Exportar datos" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Exportar datos") }
        FilledTonalButton(
            onClick  = { ultimoClick = "Calcular costos" },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Calcular costos") }
        Button(
            onClick  = { },
            enabled  = false,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Línea deshabilitada") }
        HorizontalDivider()
        EtiquetaSeccion("Acciones rápidas")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(onClick = { ultimoClick = "Agregar máquina" }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar máquina")
            }
            IconButton(onClick = { ultimoClick = "Eliminar pieza" }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar pieza",
                    tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S03_Preview() {
    MaterialTheme { S03_ButtonScreen() }
}
