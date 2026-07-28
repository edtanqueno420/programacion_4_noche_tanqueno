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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso01Suma : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_Paso01_SumaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_Paso01_SumaScreen(modifier: Modifier = Modifier) {
    var valorA by remember { mutableStateOf("") }
    var valorB by remember { mutableStateOf("") }

    val resultado = valorA.toDoubleOrNull()?.let { a ->
        valorB.toDoubleOrNull()?.let { b -> a + b }
    }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Paso 1 - Suma con UI",
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)

        HorizontalDivider()

        OutlinedTextField(
            value         = valorA,
            onValueChange = { valorA = it },
            label         = { Text("Numero A") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine    = true,
            modifier      = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value         = valorB,
            onValueChange = { valorB = it },
            label         = { Text("Numero B") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine    = true,
            modifier      = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value         = resultado?.toString() ?: "",
            onValueChange = {},
            readOnly      = true,
            label         = { Text("Resultado (A + B)") },
            modifier      = Modifier.fillMaxWidth()
        )

        Button(
            onClick   = { /* resultado se actualiza en tiempo real */ },
            enabled   = resultado != null,
            modifier  = Modifier.fillMaxWidth(),
            colors    = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                "Calcular: $resultado",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        TextButton(onClick = { valorA = ""; valorB = "" },
                   modifier = Modifier.fillMaxWidth()) {
            Text("Limpiar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso01_SumaPreview() {
    CPComposeTheme { CP_Paso01_SumaScreen() }
}
