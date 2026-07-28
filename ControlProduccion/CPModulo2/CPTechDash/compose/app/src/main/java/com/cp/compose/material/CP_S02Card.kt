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

class CP_MainActivityS02 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_S02_CardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_S02_CardScreen(modifier: Modifier = Modifier) {
    var seleccion by remember { mutableStateOf("Ninguna") }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("S02 - Card y Selector", style = MaterialTheme.typography.titleMedium)
        HorizontalDivider()

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Seleccion actual: $seleccion")
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(selected = seleccion == "Frenos",
                        onClick = { seleccion = if (seleccion == "Frenos") "Ninguna" else "Frenos" },
                        label = { Text("Frenos") })
                    FilterChip(selected = seleccion == "Motor",
                        onClick = { seleccion = if (seleccion == "Motor") "Ninguna" else "Motor" },
                        label = { Text("Motor") })
                    FilterChip(selected = seleccion == "Suspension",
                        onClick = { seleccion = if (seleccion == "Suspension") "Ninguna" else "Suspension" },
                        label = { Text("Suspension") })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S02_Preview() {
    MaterialTheme { CP_S02_CardScreen() }
}
