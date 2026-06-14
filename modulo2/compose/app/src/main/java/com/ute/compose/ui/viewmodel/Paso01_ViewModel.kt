package com.ute.compose.ui.viewmodel

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
import com.ute.compose.model.Producto
import com.ute.compose.viewmodel.ProductosViewModel

@Composable
fun Paso01_ViewModelScreen(
    // viewModel() crea o recupera el ViewModel del mismo ciclo de vida
    // Si el ViewModel ya existe (ej: tras rotar), devuelve el mismo
    vm: ProductosViewModel = viewModel()
) {
    // collectAsStateWithLifecycle: observa el StateFlow y recompone cuando cambia
    // Pausa la recolección cuando la UI no está activa (ahorra batería)
    val productos by vm.productos.collectAsStateWithLifecycle()
    val busqueda  by vm.busqueda.collectAsStateWithLifecycle()
    val cargando  by vm.cargando.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Paso 1 · ViewModel + StateFlow",
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )

        // El composable NO llama a la lógica directamente
        // Solo delega al ViewModel: vm.actualizarBusqueda(it)
        OutlinedTextField(
            value         = busqueda,
            onValueChange = { vm.actualizarBusqueda(it) },
            placeholder   = { Text("Buscar producto...") },
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

        // Loading state — mientras carga mostramos indicador
        if (cargando) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        // Contador de resultados — derivado del StateFlow
        Text(
            "${productos.size} producto(s)",
            style    = MaterialTheme.typography.labelSmall,
            color    = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LazyColumn(
            contentPadding      = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productos, key = { it.id }) { producto ->
                TarjetaProductoSimple(producto = producto)
            }
        }
    }
}

// Composable puro — recibe datos, no accede al ViewModel directamente
// Esto facilita el testing y la reutilización
@Composable
internal fun TarjetaProductoSimple(
    producto: Producto,
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
                Text(producto.nombre, fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleSmall)
                Text(producto.categoria,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Stock: ${producto.stock}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    "$${"%.2f".format(producto.precio)}",
                    style      = MaterialTheme.typography.titleMedium,
                    color      = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                if (!producto.activo) {
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