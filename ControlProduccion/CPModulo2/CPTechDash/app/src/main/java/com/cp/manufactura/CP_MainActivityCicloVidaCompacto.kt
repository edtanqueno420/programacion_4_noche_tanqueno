package com.cp.manufactura

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cp.manufactura.ui.theme.CPControlProduccionTheme

class CP_MainActivityCicloVidaCompacto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("CicloProduccion", "onCreate")
        setContent {
            CPControlProduccionTheme {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Rota la pantalla y observa Logcat")
                }
            }
        }
    }
    override fun onStart()   { super.onStart();   Log.d("CicloProduccion", "onStart") }
    override fun onResume()  { super.onResume();  Log.d("CicloProduccion", "onResume") }
    override fun onPause()   { super.onPause();   Log.d("CicloProduccion", "onPause") }
    override fun onStop()    { super.onStop();    Log.d("CicloProduccion", "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d("CicloProduccion", "onDestroy") }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("CicloProduccion", "onSaveInstanceState")
    }
}
