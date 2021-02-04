package com.example.customviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customviewapp.custom_view.SelectorCantidadesView

class MainActivity : AppCompatActivity() {
    private lateinit var selectorCantidades: SelectorCantidadesView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtener referencia
        selectorCantidades = findViewById(R.id.selectorCantidades)
        //Obtener número mediante función
        val numero = selectorCantidades.obtenerNumero()
    }
}