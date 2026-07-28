package com.cp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso02Card : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_Paso02_CardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_Paso02_CardScreen(modifier: Modifier = Modifier) {
    var seleccion by remember { mutableStateOf("") }

    Column(
        modifier  = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Paso 2 - Card & Selector",
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)

        HorizontalDivider()

        ElevatedCard(
            modifier  = Modifier.fillMaxWidth(),
            colors    = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("ElevatedCard", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text("Seleccion: $seleccion",
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }

        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("OutlinedCard", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(selected = seleccion == "Frenos",
                        onClick = { seleccion = if (seleccion == "Frenos") "" else "Frenos" },
                        label = { Text("Frenos") })
                    FilterChip(selected = seleccion == "Embrague",
                        onClick = { seleccion = if (seleccion == "Embrague") "" else "Embrague" },
                        label = { Text("Embrague") })
                    FilterChip(selected = seleccion == "Suspension",
                        onClick = { seleccion = if (seleccion == "Suspension") "" else "Suspension" },
                        label = { Text("Suspension") })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso02_CardPreview() {
    CPComposeTheme { CP_Paso02_CardScreen() }
}
