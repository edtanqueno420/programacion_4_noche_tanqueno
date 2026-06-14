@file:OptIn(ExperimentalMaterial3Api::class)

package com.cp.manufactura.material

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.manufactura.model.Pieza
import com.cp.manufactura.model.piezasDeMuestra

data class DestinoNav(
    val ruta:          String,
    val etiqueta:      String,
    val iconoActivo:   ImageVector,
    val iconoInactivo: ImageVector
)

@Composable
fun Paso05_NavBarScreen() {
    var destinoActual by remember { mutableStateOf("piezas") }
    var piezas     by remember { mutableStateOf(piezasDeMuestra) }

    val destinos = listOf(
        DestinoNav("piezas", "Piezas", Icons.Filled.Handyman,       Icons.Outlined.Handyman),
        DestinoNav("maquinas", "Máquinas", Icons.Filled.PrecisionManufacturing,     Icons.Outlined.PrecisionManufacturing),
        DestinoNav("reportes", "Reportes", Icons.Filled.Assessment, Icons.Outlined.Assessment),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CPManufactura", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },

        bottomBar = {
            NavigationBar {
                destinos.forEach { destino ->
                    val seleccionado = destinoActual == destino.ruta
                    NavigationBarItem(
                        selected = seleccionado,
                        onClick  = { destinoActual = destino.ruta },
                        icon     = {
                            Icon(
                                imageVector        = if (seleccionado) destino.iconoActivo
                                else destino.iconoInactivo,
                                contentDescription = destino.etiqueta
                            )
                        },
                        label = { Text(destino.etiqueta) }
                    )
                }
            }
        },

        floatingActionButton = {
            if (destinoActual == "piezas") {
                FloatingActionButton(onClick = { }) {
                    Icon(Icons.Default.Add, "Nueva pieza")
                }
            }
        }

    ) { paddingValues ->
        when (destinoActual) {
            "piezas" -> PantallaPiezasContent(
                piezas  = piezas,
                onActivo = { id ->
                    piezas = piezas.map { c ->
                        if (c.id == id) c.copy(activo = !c.activo) else c
                    }
                },
                modifier   = Modifier.padding(paddingValues)
            )
            "maquinas" -> PantallaMaquinasContent(
                modifier  = Modifier.padding(paddingValues)
            )
            "reportes"    -> PantallaReportesContent(
                modifier  = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun PantallaPiezasContent(
    piezas:  List<Pieza>,
    onActivo: (Int) -> Unit,
    modifier:   Modifier = Modifier
) {
    LazyColumn(
        modifier            = modifier,
        contentPadding      = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(piezas, key = { it.id }) { pieza ->
            TarjetaPieza(
                pieza   = pieza,
                onFavorito = { onActivo(pieza.id) }
            )
        }
        item { Spacer(Modifier.height(80.dp)) }
    }
}

@Composable
fun PantallaMaquinasContent(
    modifier:  Modifier = Modifier
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.PrecisionManufacturing, null,
                Modifier.size(56.dp), tint = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(12.dp))
            Text("Máquinas",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("Próximamente...",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun PantallaReportesContent(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Assessment, null, Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(12.dp))
            Text("Reportes", style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
            Text("Próximamente...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Paso05_Preview() {
    MaterialTheme { Paso05_NavBarScreen() }
}
