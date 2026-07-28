package com.cp.manufactura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cp.manufactura.ui.permisos.CP_PantallaPermisos
import com.cp.manufactura.ui.theme.CPControlProduccionTheme

class CP_MainActivityPermisosMaquinaria : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CPControlProduccionTheme {
                CP_PantallaPermisos(
                    onTodosConcedidos = {}
                )
            }
        }
    }
}
