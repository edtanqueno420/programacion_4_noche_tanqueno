@file:OptIn(ExperimentalMaterial3Api::class)

package com.cp.manufactura.material

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.manufactura.model.piezasDeMuestra

@Composable
fun Paso04_ScaffoldScreen() {
    var piezas  by remember { mutableStateOf(piezasDeMuestra) }
    var busqueda   by remember { mutableStateOf("") }
    var filtro     by remember { mutableStateOf("Todas") }
    var mostrarFab by remember { mutableStateOf(false) }

    val piezasFiltradas = piezas
        .filter { c -> if (filtro == "Activas") c.activo else true }
        .filter { c ->
            busqueda.isBlank() ||
                    c.nombre.contains(busqueda, ignoreCase = true)
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Piezas (${piezas.size})",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {
                        filtro = if (filtro == "Activas") "Todas" else "Activas"
                    }) {
                        Icon(
                            imageVector        = if (filtro == "Activas")
                                Icons.Default.CheckCircle else Icons.Default.Circle,
                            contentDescription = "Filtrar activas",
                            tint               = if (filtro == "Activas")
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarFab = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nueva pieza")
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value         = busqueda,
                onValueChange = { busqueda = it },
                placeholder   = { Text("Buscar pieza...") },
                leadingIcon   = { Icon(Icons.Default.Search, null) },
                trailingIcon  = {
                    if (busqueda.isNotEmpty())
                        IconButton(onClick = { busqueda = "" }) {
                            Icon(Icons.Default.Clear, "Limpiar")
                        }
                },
                singleLine = true,
                modifier   = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding        = PaddingValues(horizontal = 16.dp)
            ) {
                items(listOf("Todas", "Activas")) { opcion ->
                    FilterChip(
                        selected = filtro == opcion,
                        onClick  = { filtro = opcion },
                        label    = { Text(opcion) },
                        leadingIcon = if (filtro == opcion) {{
                            Icon(Icons.Default.Check, null,
                                Modifier.size(FilterChipDefaults.IconSize))
                        }} else null
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            LazyColumn(
                contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text("${piezasFiltradas.size} resultado(s)",
                        style    = MaterialTheme.typography.labelSmall,
                        color    = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 4.dp))
                }
                items(piezasFiltradas, key = { it.id }) { pieza ->
                    TarjetaPieza(
                        pieza  = pieza,
                        onFavorito = {
                            piezas = piezas.map { c ->
                                if (c.id == pieza.id) c.copy(activo = !c.activo) else c
                            }
                        }
                    )
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }

    if (mostrarFab) {
        AlertDialog(
            onDismissRequest = { mostrarFab = false },
            title   = { Text("Nueva pieza") },
            text    = { Text("Formulario de registro de piezas (próximamente).") },
            confirmButton = {
                TextButton(onClick = { mostrarFab = false }) { Text("OK") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Paso04_Preview() {
    MaterialTheme { Paso04_ScaffoldScreen() }
}
