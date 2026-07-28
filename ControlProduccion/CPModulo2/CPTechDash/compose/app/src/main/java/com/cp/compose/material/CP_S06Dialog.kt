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

class CP_MainActivityS06 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_S06_DialogScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_S06_DialogScreen(modifier: Modifier = Modifier) {
    var mostrar by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    if (mostrar) {
        AlertDialog(
            onDismissRequest = { mostrar = false },
            title = { Text("Confirmar") },
            text  = { Text("Accion en Control de Produccion") },
            confirmButton = {
                TextButton(onClick = { mostrar = false; resultado = "Confirmado" }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrar = false; resultado = "Cancelado" }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("S06 - Dialog", style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()
        Text("Resultado: $resultado")
        Button(onClick = { mostrar = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Abrir Dialogo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S06_Preview() {
    MaterialTheme { CP_S06_DialogScreen() }
}
