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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso05NavBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                CP_Paso05_NavBarScreen()
            }
        }
    }
}

sealed class CP_Ruta(val ruta: String, val titulo: String, val icono: @Composable () -> Unit) {
    data object CP_Inicio     : CP_Ruta("inicio",     "Inicio",     { Icon(Icons.Default.Home,      null) })
    data object CP_Inventario : CP_Ruta("inventario", "Inventario", { Icon(Icons.Default.Inventory, null) })
    data object CP_Ajustes    : CP_Ruta("ajustes",    "Ajustes",    { Icon(Icons.Default.Settings,  null) })
}

@Composable
fun CP_Paso05_NavBarScreen() {
    var rutaActual by remember { mutableStateOf(CP_Ruta.CP_Inicio.ruta) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(CP_Ruta.CP_Inicio, CP_Ruta.CP_Inventario, CP_Ruta.CP_Ajustes).forEach { ruta ->
                    NavigationBarItem(
                        icon   = ruta.icono,
                        label  = { Text(ruta.titulo) },
                        selected = rutaActual == ruta.ruta,
                        onClick  = { rutaActual = ruta.ruta }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding).padding(16.dp)) {
            Text("Paso 5 - BottomNavBar", style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(Modifier.padding(vertical = 8.dp))
            Text("Pestana activa: $rutaActual")
            Spacer(Modifier.height(8.dp))

            when (rutaActual) {
                CP_Ruta.CP_Inicio.ruta -> {
                    Card(Modifier.fillMaxWidth()) {
                        ListItem(
                            headlineContent = { Text("Produccion HOY", fontWeight = FontWeight.Bold) },
                            supportingContent = { Text("32 lotes activos, 98.2% eficiencia") },
                            leadingContent = {
                                Icon(Icons.Default.Home, null, tint = MaterialTheme.colorScheme.primary)
                            }
                        )
                    }
                }
                CP_Ruta.CP_Inventario.ruta -> {
                    Card(Modifier.fillMaxWidth()) {
                        ListItem(
                            headlineContent = { Text("Inventario", fontWeight = FontWeight.Bold) },
                            supportingContent = { Text("2,847 piezas en stock, 45 productos") },
                            leadingContent = {
                                Icon(Icons.Default.Inventory, null, tint = MaterialTheme.colorScheme.primary)
                            }
                        )
                    }
                }
                CP_Ruta.CP_Ajustes.ruta -> {
                    Card(Modifier.fillMaxWidth()) {
                        ListItem(
                            headlineContent = { Text("Ajustes", fontWeight = FontWeight.Bold) },
                            supportingContent = { Text("Configuracion del sistema de planta") },
                            leadingContent = {
                                Icon(Icons.Default.Settings, null, tint = MaterialTheme.colorScheme.primary)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso05_Preview() {
    CPComposeTheme { CP_Paso05_NavBarScreen() }
}
