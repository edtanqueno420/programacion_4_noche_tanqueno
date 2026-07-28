package com.cp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso06Dialogos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    CP_Paso06_DialogosScreen()
                }
            }
        }
    }
}

@Composable
fun CP_Paso06_DialogosScreen() {
    var mostrarDialogo1 by remember { mutableStateOf(false) }
    var mostrarDialogo2 by remember { mutableStateOf(false) }
    var mostrarDialogo3 by remember { mutableStateOf(false) }
    var resultado       by remember { mutableStateOf("") }

    if (mostrarDialogo1) {
        CP_MiDialogoBasico(
            onDismiss  = { mostrarDialogo1 = false },
            onAceptar  = { mostrarDialogo1 = false; resultado = "Aceptado" },
            onCancelar = { mostrarDialogo1 = false; resultado = "Cancelado" }
        )
    }

    if (mostrarDialogo2) {
        CP_MiDialogoSeleccion(
            opciones   = listOf("Frenos", "Embrague", "Suspension", "Motor"),
            onSeleccion = { mostrarDialogo2 = false; resultado = "Seleccion: $it" },
            onDismiss   = { mostrarDialogo2 = false }
        )
    }

    if (mostrarDialogo3) {
        CP_MiDialogoCustom(onDismiss = { mostrarDialogo3 = false })
    }

    Scaffold { padding ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {
            Text("Dialogos", style = MaterialTheme.typography.titleMedium)
            HorizontalDivider()

            Text("Resultado: $resultado", style = MaterialTheme.typography.bodyMedium)

            androidx.compose.material3.OutlinedButton(
                onClick = { mostrarDialogo1 = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Dialogo Basico (Yes/No)") }

            androidx.compose.material3.OutlinedButton(
                onClick = { mostrarDialogo2 = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Dialogo Seleccion") }

            androidx.compose.material3.OutlinedButton(
                onClick = { mostrarDialogo3 = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Dialogo Custom")
            }
        }
    }
}

@Composable
fun CP_MiDialogoBasico(
    onAceptar:  () -> Unit,
    onCancelar: () -> Unit,
    onDismiss:  () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirmar") },
        text  = { Text("Deseas guardar el registro?") },
        confirmButton = { TextButton(onClick = onAceptar) { Text("Aceptar") } },
        dismissButton = { TextButton(onClick = onCancelar) { Text("Cancelar") } }
    )
}

@Composable
fun CP_MiDialogoSeleccion(
    opciones:    List<String>,
    onSeleccion: (String) -> Unit,
    onDismiss:   () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Seleccionar componente") },
        text  = {
            androidx.compose.foundation.layout.Column {
                opciones.forEach { opcion ->
                    androidx.compose.material3.TextButton(onClick = { onSeleccion(opcion) }) {
                        Text(opcion)
                    }
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
fun CP_MiDialogoCustom(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title    = { Text("Dialogo Custom") },
        text     = { Text("Este es un dialogo personalizado con tema de Control de Produccion") },
        confirmButton = { TextButton(onClick = onDismiss) { Text("OK") } }
    )
}

@Preview(showBackground = true)
@Composable
fun CP_Paso06_Preview() {
    CPComposeTheme { CP_Paso06_DialogosScreen() }
}
