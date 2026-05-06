// MainActivity.kt
package com.ute.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.ute.compose.screens.*
import com.ute.compose.material.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // ◀ CAMBIA AQUÍ para probar cada sección:
                // S01_SaludoScreen()
                // S02_TextScreen()
                // S03_ButtonScreen()
                // S04_LayoutScreen()
                // S05_ModifierScreen()
                //S06_EstadoScreen()
                // S07_StateHoistingScreen()
                //S08_BienvenidaScreen()

                // Componentes Material 3: TextField, Card, LazyColumn, Scaffold y diálogos
                // ◀ CAMBIA AQUÍ para probar cada paso:
                // Paso01TextFieldScreen()
                Paso02_CardScreen()
                // Paso03_LazyColumnScreen()
                // Paso04_ScaffoldScreen()
                // Paso05_NavBarScreen()
                // Paso06_DialogosScreen()   // ← paso activo
            }
        }
    }
}