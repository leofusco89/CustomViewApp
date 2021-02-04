package com.example.customviewapp.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.customviewapp.R

class SelectorCantidadesView @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    var defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var cantidadMaxima = CANTIDAD_MAXIMA
    private var cantidadMinima = CANTIDAD_MINIMA
    private lateinit var imgAgregar: ImageButton
    private lateinit var imgRemover: ImageButton
    private lateinit var txtNumero: TextView


    private companion object {
        const val CANTIDAD_MAXIMA = 100
        const val CANTIDAD_MINIMA = 0
    }

    init {
        View.inflate(context, R.layout.view_selector_cantidades, this)

        //Traemos las constantes que se usan para esta View
        initializeAttrs()

        //Inicializamos los objetos así esta clase funciona como Kotlin, que ya importa las
        //referencias
        setupUI()

        //Agregamos listeners y la funcionalidad de sumar y restar
        setupClicklListeners()
    }

    private fun initializeAttrs() {
        attrs?.let {
            //Declaramos variable para obtener los estilos del atributo
            val typedArray =
                context.theme.obtainStyledAttributes(
                    it,
                    R.styleable.selectorCantidadesView, //Nombre del estilo en attrs.xml
                    defStyleAttr, //Esto es por si asignan un Theme en el layout
                    //podemos tomar ese, o no permitir tomar uno como en este caso
                    0
                )

            //Obtenemos el valor cargado en el layout y sino un valor default, en este caso, 100
            cantidadMaxima =
                typedArray.getInt(
                    R.styleable.selectorCantidadesView_cantidadMaxima,
                    CANTIDAD_MAXIMA
                )
            cantidadMinima =
                typedArray.getInt(
                    R.styleable.selectorCantidadesView_cantidadMinima,
                    CANTIDAD_MINIMA
                )
        }
    }

    private fun setupUI() {
        //Inicializamos los objetos así esta clase funciona como Kotlin, que ya importa las
        //referencias
        imgAgregar = findViewById(R.id.imgAgregar)
        imgRemover = findViewById(R.id.imgRemover)
        txtNumero = findViewById(R.id.txtNumero)
    }

    private fun setupClicklListeners() {
        imgAgregar.setOnClickListener { agregar() }
        imgRemover.setOnClickListener { remover() }
    }

    private fun agregar() {
        //Sumamos una unidad salvo que se llegue a cantidad máxima, dónde
        //mostramos mensaje de error
        var numeroActual = txtNumero.text.toString().toInt()
        if (numeroActual < cantidadMaxima) {
            numeroActual++
            txtNumero.text = numeroActual.toString()
        } else {
            Toast.makeText(context, "Cantidad máxima alcanzada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun remover() {
        //Restamos una unidad salvo que se llegue a cantidad mínima, dónde
        //mostramos mensaje de error
        var numeroActual = txtNumero.text.toString().toInt()
        if (numeroActual >= cantidadMaxima) {
            numeroActual--
            txtNumero.text = numeroActual.toString()
        } else {
            Toast.makeText(context, "Cantidad mínima alcanzada", Toast.LENGTH_SHORT).show()
        }
    }

    public fun obtenerNumero(): Int {
        //Función para devolver número
        return txtNumero.text.toString().toInt()
    }
}