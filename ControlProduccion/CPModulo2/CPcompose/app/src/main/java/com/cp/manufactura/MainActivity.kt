package com.cp.manufactura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.cp.manufactura.material.*
import com.cp.manufactura.screens.*
import com.cp.manufactura.ui.theme.CPManufacturaTheme
import com.cp.manufactura.ui.viewmodel.Paso01_ViewModelScreen
import com.cp.manufactura.ui.viewmodel.Paso02_UiStateScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CPManufacturaTheme {
                Paso01SumaScreen()
            }
        }
    }
}
