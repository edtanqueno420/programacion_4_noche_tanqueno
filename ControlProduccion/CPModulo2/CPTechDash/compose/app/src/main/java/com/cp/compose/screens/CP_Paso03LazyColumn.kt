package com.cp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.model.CP_Producto
import com.cp.compose.model.CP_productosDeMuestra
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityPaso03LazyColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_Paso03_LazyColumnScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_Paso03_LazyColumnScreen(modifier: Modifier = Modifier) {
    var seleccion by remember { mutableStateOf<CP_Producto?>(null) }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Paso 3 - LazyColumn & Scroll",
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)

        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        Text("${CP_productosDeMuestra.size} productos disponibles",
            style    = MaterialTheme.typography.labelSmall,
            color    = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp))

        LazyColumn(
            contentPadding      = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier            = Modifier.weight(1f)
        ) {
            items(CP_productosDeMuestra, key = { it.id }) { producto ->
                val isSelected = seleccion?.id == producto.id
                Card(
                    modifier  = Modifier.fillMaxWidth(),
                    colors    = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surface
                    ),
                    onClick   = { seleccion = if (isSelected) null else producto }
                ) {
                    Row(
                        modifier              = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(producto.nombre, fontWeight = FontWeight.SemiBold)
                            Text(producto.categoria,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Text("$${"%.2f".format(producto.precio)}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }

        seleccion?.let { prod ->
            HorizontalDivider(Modifier.padding(vertical = 8.dp))
            Text("Seleccionado: ${prod.nombre}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_Paso03_Preview() {
    CPComposeTheme { CP_Paso03_LazyColumnScreen() }
}
