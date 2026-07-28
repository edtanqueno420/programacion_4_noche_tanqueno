package com.cp.compose.material

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityS04 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_S04_TextField(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_S04_TextField(modifier: Modifier = Modifier) {
    var valor by remember { mutableStateOf("") }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("S04 - TextField", style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()

        OutlinedTextField(
            value         = valor,
            onValueChange = { valor = it },
            label         = { Text("Componente") },
            leadingIcon   = { Icon(Icons.Default.Build, null) },
            trailingIcon  = {
                if (valor.isNotEmpty())
                    IconButton(onClick = { valor = "" }) {
                        Icon(Icons.Default.Clear, "Limpiar")
                    }
            },
            singleLine = true,
            modifier   = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value         = valor,
            onValueChange = {},
            readOnly      = true,
            label         = { Text("Vista previa") },
            modifier      = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S04_Preview() {
    MaterialTheme { CP_S04_TextField() }
}
