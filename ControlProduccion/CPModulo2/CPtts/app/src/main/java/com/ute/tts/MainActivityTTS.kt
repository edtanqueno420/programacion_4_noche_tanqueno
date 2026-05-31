package com.ute.tts

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class MainActivityTTS : AppCompatActivity(), OnInitListener {
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_v2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            var text = findViewById<EditText>(R.id.editTextText).text.toString()
            if (text.isNotEmpty()) {
                Log.i("CP_TTS", "Codigo/OP leido: $text")
            } else {
                text = "Ingrese un codigo de producto o numero de orden de produccion"
            }
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }

        tts = TextToSpeech(this, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.setLanguage(Locale("ES"))
            findViewById<TextView>(R.id.textView).text = "TTS listo para lectura de ordenes"
        } else {
            findViewById<TextView>(R.id.textView).text = "TTS no disponible"
        }
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }
}
