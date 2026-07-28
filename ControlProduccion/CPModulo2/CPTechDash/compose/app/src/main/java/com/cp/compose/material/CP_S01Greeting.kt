package com.cp.compose.material

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityS01 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_S01_GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CP_S01_GreetingScreen(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var mostrarDetalle by remember { mutableStateOf(false) }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("S01 - Greeting y Estado",
            style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()

        OutlinedTextField(
            value         = nombre,
            onValueChange = { nombre = it },
            label         = { Text("Nombre del operador") },
            singleLine    = true,
            modifier      = Modifier.fillMaxWidth()
        )

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Saludo: Hola, $nombre")
                Text("Estado del checkbox: $mostrarDetalle")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            FilterChip(selected = mostrarDetalle,
                onClick = { mostrarDetalle = !mostrarDetalle },
                label = { Text("Mostrar detalle") })
            FilterChip(selected = !mostrarDetalle,
                onClick = { mostrarDetalle = !mostrarDetalle },
                label = { Text("Ocultar detalle") })
        }

        Button(onClick = { nombre = "Planta" },
               modifier = Modifier.fillMaxWidth()) {
            Text("Preset: Planta")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S01_Preview() {
    MaterialTheme { CP_S01_GreetingScreen() }
}
