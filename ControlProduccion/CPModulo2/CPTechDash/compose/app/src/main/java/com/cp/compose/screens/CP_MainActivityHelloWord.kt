package com.cp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityHelloWord : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_PantallaHelloWorld(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_PantallaHelloWorld(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Hello World - Manufactura", style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()

        OutlinedTextField(
            value         = nombre,
            onValueChange = { nombre = it },
            label         = { Text("Nombre") },
            singleLine    = true,
            modifier      = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value         = resultado,
            onValueChange = {},
            readOnly      = true,
            label         = { Text("Salida") },
            modifier      = Modifier.fillMaxWidth()
        )

        Button(onClick = { resultado = "Hola, $nombre" },
               modifier = Modifier.fillMaxWidth()) {
            Text("Enviar")
        }

        TextButton(onClick = { nombre = ""; resultado = "" },
                   modifier = Modifier.fillMaxWidth()) {
            Text("Limpiar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_PantallaHelloWorldPreview() {
    CPComposeTheme { CP_PantallaHelloWorld() }
}
