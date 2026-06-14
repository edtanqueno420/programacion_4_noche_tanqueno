package com.cp.manufactura.ui.viewmodel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cp.manufactura.model.Pieza
import com.cp.manufactura.viewmodel.PiezasViewModel

@Composable
fun Paso01_ViewModelScreen(
    vm: PiezasViewModel = viewModel()
) {
    val piezas by vm.piezas.collectAsStateWithLifecycle()
    val busqueda  by vm.busqueda.collectAsStateWithLifecycle()
    val cargando  by vm.cargando.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Paso 1 · ViewModel + StateFlow",
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value         = busqueda,
            onValueChange = { vm.actualizarBusqueda(it) },
            placeholder   = { Text("Buscar pieza...") },
            leadingIcon   = { Icon(Icons.Default.Search, null) },
            trailingIcon  = {
                if (busqueda.isNotEmpty())
                    IconButton(onClick = { vm.actualizarBusqueda("") }) {
                        Icon(Icons.Default.Clear, "Limpiar")
                    }
            },
            singleLine = true,
            modifier   = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        if (cargando) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        Text(
            "${piezas.size} pieza(s)",
            style    = MaterialTheme.typography.labelSmall,
            color    = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LazyColumn(
            contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(piezas, key = { it.id }) { pieza ->
                TarjetaPiezaSimple(pieza = pieza)
            }
        }
    }
}

@Composable
internal fun TarjetaPiezaSimple(
    pieza: Pieza,
    onClick:  () -> Unit = {}
) {
    ElevatedCard(
        onClick  = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier              = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(pieza.nombre, fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleSmall)
                Text(pieza.categoria,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Stock: ${pieza.stock}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "$${"%.2f".format(pieza.precio)}",
                    style      = MaterialTheme.typography.titleMedium,
                    color      = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                if (!pieza.activo) {
                    AssistChip(
                        onClick = {},
                        label   = { Text("Inactivo",
                            style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Paso01_Preview() {
    MaterialTheme { Paso01_ViewModelScreen() }
}
