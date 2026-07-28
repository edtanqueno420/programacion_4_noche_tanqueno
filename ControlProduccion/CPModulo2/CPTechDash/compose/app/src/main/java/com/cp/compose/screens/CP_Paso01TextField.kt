package com.cp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso01TextField : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_Paso01_TextFieldScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_Paso01_TextFieldScreen(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Paso 1 - TextField",
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)

        HorizontalDivider()

        OutlinedTextField(
            value         = nombre,
            onValueChange = { nombre = it },
            label         = { Text("Nombre") },
            placeholder   = { Text("Ej. Linea de produccion") },
            singleLine    = true,
            modifier      = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value         = "Hola, $nombre",
            onValueChange = {},
            readOnly      = true,
            label         = { Text("Salida") },
            modifier      = Modifier.fillMaxWidth()
        )

        Button(onClick = { nombre = nombre.uppercase() },
               modifier = Modifier.fillMaxWidth()) {
            Text("Mayusculas")
        }

        TextButton(onClick = { nombre = "" },
                   modifier = Modifier.fillMaxWidth()) {
            Text("Limpiar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso01_TextFieldPreview() {
    CPComposeTheme { CP_Paso01_TextFieldScreen() }
}
