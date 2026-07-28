package com.cp.compose.material

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.model.CP_Producto
import com.cp.compose.model.CP_productosDeMuestra
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityS03 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CP_S03_LazyColumnScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CP_S03_LazyColumnScreen(modifier: Modifier = Modifier) {
    var seleccion by remember { mutableStateOf<CP_Producto?>(null) }

    Column(modifier = modifier.padding(16.dp)) {
        Text("S03 - LazyColumn", style = MaterialTheme.typography.titleMedium)
        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        LazyColumn(
            contentPadding      = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier            = Modifier.weight(1f)
        ) {
            items(CP_productosDeMuestra, key = { it.id }) { prod ->
                val isSelected = seleccion?.id == prod.id
                Card(
                    modifier  = Modifier.fillMaxWidth(),
                    colors    = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surface
                    ),
                    onClick   = { seleccion = if (isSelected) null else prod }
                ) {
                    ListItem(
                        headlineContent   = { Text(prod.nombre) },
                        supportingContent = { Text("${prod.categoria} - Stock: ${prod.stock}") }
                    )
                }
            }
        }

        seleccion?.let { prod ->
            HorizontalDivider(Modifier.padding(vertical = 4.dp))
            Text("Seleccion: ${prod.nombre}",
                modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S03_Preview() {
    MaterialTheme { CP_S03_LazyColumnScreen() }
}
