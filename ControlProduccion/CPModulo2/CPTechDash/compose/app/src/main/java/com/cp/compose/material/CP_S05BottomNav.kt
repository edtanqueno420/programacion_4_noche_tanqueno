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

class CP_MainActivityS05 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                CP_S05_BottomNavScreen()
            }
        }
    }
}

sealed class CP_S5_Ruta(val ruta: String, val titulo: String, val icono: @Composable () -> Unit) {
    data object CP_Inicio     : CP_S5_Ruta("inicio",     "Inicio",     { Icon(Icons.Default.Home,      null) })
    data object CP_Inventario : CP_S5_Ruta("inventario", "Inventario", { Icon(Icons.Default.Inventory, null) })
    data object CP_Ajustes    : CP_S5_Ruta("ajustes",    "Ajustes",    { Icon(Icons.Default.Settings,  null) })
}

@Composable
fun CP_S05_BottomNavScreen() {
    var rutaActual by remember { mutableStateOf(CP_S5_Ruta.CP_Inicio.ruta) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(CP_S5_Ruta.CP_Inicio, CP_S5_Ruta.CP_Inventario, CP_S5_Ruta.CP_Ajustes).forEach { ruta ->
                    NavigationBarItem(
                        icon     = ruta.icono,
                        label    = { Text(ruta.titulo) },
                        selected = rutaActual == ruta.ruta,
                        onClick  = { rutaActual = ruta.ruta }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding).padding(16.dp)) {
            Text("S05 - BottomNav", style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(Modifier.padding(vertical = 8.dp))
            Text("Pestana: $rutaActual")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S05_Preview() {
    MaterialTheme { CP_S05_BottomNavScreen() }
}
