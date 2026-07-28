package com.cp.compose.material

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cp.compose.ui.theme.CPComposeTheme

class CP_MainActivityS08 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPComposeTheme {
                CP_S08_BienvenidaScreen()
            }
        }
    }
}

@Composable
fun CP_S08_BienvenidaScreen() {
    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier              = Modifier.padding(24.dp),
                horizontalAlignment   = Alignment.CenterHorizontally,
                verticalArrangement   = Arrangement.spacedBy(12.dp)
            ) {
                Text("Panel de Control", style = MaterialTheme.typography.headlineSmall)
                Text("Manufactura", style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary)
                HorizontalDivider()
                Text("Bienvenido al sistema de Control de Produccion.",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CP_S08_Preview() {
    MaterialTheme { CP_S08_BienvenidaScreen() }
}
