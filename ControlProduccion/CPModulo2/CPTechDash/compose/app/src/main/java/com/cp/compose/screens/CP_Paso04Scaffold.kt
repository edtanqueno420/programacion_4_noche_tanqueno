package com.cp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso04Scaffold : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                CP_Paso04_ScaffoldScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CP_Paso04_ScaffoldScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title   = { Text("Paso 4 - Scaffold") },
                colors  = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, "Agregar")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Paso 4 - Scaffold",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())

            HorizontalDivider()

            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Scaffold Estructura", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text("topBar: TopAppBar con color primario")
                    Text("fab: FloatingActionButton con icono +")
                    Text("content: LazyColumn con tarjetas de componentes")
                }
            }

            val items = listOf(
                Triple("TopBar",   Icons.Default.Search, "Barra superior"),
                Triple("FAB",      Icons.Default.Add,   "Boton flotante"),
                Triple("Content",  Icons.Default.List,  "Contenido scrollable")
            )

            items.forEach { (titulo, icono, desc) ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    ListItem(
                        headlineContent = { Text(titulo, fontWeight = FontWeight.SemiBold) },
                        supportingContent = { Text(desc) },
                        leadingContent    = {
                            Icon(icono, null, tint = MaterialTheme.colorScheme.primary)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso04_Preview() {
    CPComposeTheme { CP_Paso04_ScaffoldScreen() }
}
