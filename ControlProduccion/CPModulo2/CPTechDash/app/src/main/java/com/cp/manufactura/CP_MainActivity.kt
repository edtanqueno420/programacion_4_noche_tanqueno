package com.cp.manufactura

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cp.manufactura.ui.multimedia.PantallaMonitoreoMaquinaria
import com.cp.manufactura.ui.theme.CPControlProduccionTheme

class CP_MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPControlProduccionTheme {
                PantallaMonitoreoMaquinaria(
                    onFotoTomada = { uri -> Log.d("CPControlProduccion", "Foto guardada: $uri") },
                    onCerrar     = {}
                )
            }
        }
    }
}
