package com.example.candi.calculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    var exadec  = -1;
    var numeros = ""
    var x = 0.0
    var y = 0.0
    var signo = ""
    var pulsacion = false
    var memoria = 0.0
    var memoriaUso = false


    fun leeNumero2 (v: View){
        val numero = findViewById<Button>(v.id)
        exadec = numero.text.toString().toInt(16)

        if (signo == "-" && x == 0.0) {
            numeros = numeros + exadec.toString(16)
            Resultado.text = signo + numeros
            pulsacion = false
        }
        else {
            if (signo != "") {
                numeros = numeros + exadec.toString(16)
                Resultado.text = numeros
                y = Resultado.text.toString().toInt(16).toDouble()
            }
            else {
                numeros = numeros + exadec.toString(16)
                Resultado.text = numeros
            }
        }
    }

    fun leeNumero (v: View){
        val numero = findViewById<Button>(v.id)

        if ( numero.text.toString().equals("0") && Resultado.text == "0") {
            Resultado.text = "0"
        } else {
            if (signo == "-" && x == 0.0) {
                numeros = numeros + numero.text.toString()
                Resultado.text = signo + numeros
                pulsacion = false
            }
            else {
                if (signo != "") {
                    numeros = numeros + numero.text.toString()
                    Resultado.text = numeros
                    y = Resultado.text.toString().toDouble()
                }
                else {
                    numeros = numeros + numero.text.toString()
                    Resultado.text=numeros
                }
            }
        }

    }

    fun M (v: View){
        if ( Resultado.text =="M+ borrada" || Resultado.text == "Imposible÷0" ) {
            Resultado.text = "0"
            pantalla2.text = ""
            signo = ""
            numeros = ""
            x = 0.0
            y = 0.0
        }
        else {
            if ( exadec != -1){
                memoria = memoria + Resultado.text.toString().toInt(16).toDouble()
            } else {
                memoria = memoria + Resultado.text.toString().toDouble()
            }
            Resultado.text = "0"
            pantalla2.text = ""
            signo = ""
            numeros = ""
            x = 0.0
            y = 0.0
            memoriaUso = true
        }
    }

    fun MR (v: View) {
        if ( exadec != -1 ) {
            Resultado.text = memoria.toInt().toString(16)
        } else {
            Resultado.text = memoria.toString()
        }
    }

    fun MC (v: View) {
        memoria = 0.0
        memoriaUso = false
        Resultado.text = "M+ borrada"
    }

    fun ce (v: View) {
        Resultado.text= "0"
        pantalla2.text = ""
        numeros = ""
        x = 0.0
        y = 0.0
        signo = ""
        pulsacion = false
    }

    fun sumar (v: View) {
        if (Resultado.text == "Imposible÷0" || Resultado.text == "M+ borrada" || signo == "=" ) {
            signo = "+"
            Resultado.text = "0"
            pantalla2.text = signo
            pulsacion = true
        }
        else {
            if (pulsacion) {
                if (memoriaUso && y == 0.0) {
                    y = memoria
                }
                when (signo) {
                    "+" -> { x = x + y }
                    "-" -> { x = x - y }
                    "×" -> { x = x * y }
                    "÷" -> { if (y == 0.0) { ce(v) ; Resultado.text = "Imposible÷0" } else{ x = x / y } }
                }
                signo = "+"
                pantalla2.text = signo
                if ( exadec != -1 ) {
                    Resultado.text = x.toInt().toString(16)
                } else {
                    Resultado.text = x.toString()
                }
                numeros = ""
            }
            else {
                signo = "+"
                pantalla2.text = signo
                if ( exadec != -1 ) {
                    x = Resultado.text.toString().toInt(16).toDouble()
                    Resultado.text = x.toInt().toString(16)
                } else {
                    x = Resultado.text.toString().toDouble()
                    Resultado.text = x.toString()
                }
                numeros = ""
                pulsacion = true
            }
        }
    }

    fun restar (v: View) {
        if ( Resultado.text == "Imposible÷0" || Resultado.text == "M+ borrada" || signo == "=" ) {
            signo = "-"
            Resultado.text = "0"
            pantalla2.text = signo
            pulsacion = true
        }
        else {
            if (pulsacion) {
                if (memoriaUso && y == 0.0) {
                    y = memoria
                }
                when (signo) {
                    "+" -> { x = x + y }
                    "-" -> { x = x - y }
                    "×" -> { x = x * y }
                    "÷" -> { if (y == 0.0) { ce(v) ; Resultado.text = "Imposible÷0" } else{ x = x / y } }
                }
                signo = "-"
                pantalla2.text = signo
                Resultado.text = x.toString()
                numeros = ""
            }
            else {
                signo = "-"
                pantalla2.text = signo
                if ( exadec != -1 ) {
                    x = Resultado.text.toString().toInt(16).toDouble()
                    Resultado.text = x.toInt().toString(16)
                } else {
                    x = Resultado.text.toString().toDouble()
                    Resultado.text = x.toString()
                }
                numeros = ""
                pulsacion = true
            }
        }
    }

    fun multiplicar (v: View) {
        if (Resultado.text == "Imposible÷0" || Resultado.text == "M+ borrada" || signo == "=" ) {
            signo = "×"
            Resultado.text = "0"
            pantalla2.text = signo
            pulsacion = true
        }
        else {
            if (pulsacion) {
                if (memoriaUso && y == 0.0) {
                    y = memoria
                }
                when (signo) {
                    "+" -> { x = x + y }
                    "-" -> { x = x - y }
                    "×" -> { x = x * y }
                    "÷" -> { if (y == 0.0) { ce(v) ; Resultado.text = "Imposible÷0" } else{ x = x / y } }
                }
                signo = "×"
                pantalla2.text = signo
                Resultado.text = x.toString()
                numeros = ""
            }
            else {
                signo = "×"
                pantalla2.text = signo
                if ( exadec != -1 ) {
                    x = Resultado.text.toString().toInt(16).toDouble()
                    Resultado.text = x.toInt().toString(16)
                } else {
                    x = Resultado.text.toString().toDouble()
                    Resultado.text = x.toString()
                }
                numeros = ""
                pulsacion = true
            }
        }
    }

    fun dividir (v: View) {
        if (Resultado.text == "Imposible÷0" || Resultado.text == "M+ borrada" || signo == "=" ) {
            signo = "÷"
            Resultado.text = "0"
            pantalla2.text = signo
            pulsacion = true
        }
        else {
            if (pulsacion) {
                if (memoriaUso && y == 0.0) {
                    y = memoria
                }
                when (signo) {
                    "+" -> { x = x + y }
                    "-" -> { x = x - y }
                    "×" -> { x = x * y }
                    "÷" -> { if (y == 0.0) { ce(v) ; Resultado.text = "Imposible÷0" } else{ x = x / y } }
                }
                signo = "÷"
                pantalla2.text = signo
                Resultado.text = x.toString()
                numeros = ""

            }
            else {
                signo = "÷"
                pantalla2.text = signo
                if ( exadec != -1 ) {
                    x = Resultado.text.toString().toInt(16).toDouble()
                    Resultado.text = x.toInt().toString(16)
                } else {
                    x = Resultado.text.toString().toDouble()
                    Resultado.text = x.toString()
                }
                numeros = ""
                pulsacion = true
            }
        }
    }

    fun calcular (v: View) {
        Resultado.text = ""
        pantalla2.text = "="
        if (memoriaUso && y == 0.0) {
            y = memoria
        }
        if ( signo == "-" && y == 0.0) {
            Resultado.text = signo + numeros
        } else {
            when (signo) {
                "+" -> {
                    x = x + y
                    if ( exadec != -1 ) {
                        Resultado.text = x.toInt().toString(16)
                    } else {
                        Resultado.text = x.toString()
                    }
                    pulsacion = false
                }
                "-" -> {
                    x = x - y
                    if ( exadec != -1 ) {
                        Resultado.text = x.toInt().toString(16)
                    } else {
                        Resultado.text = x.toString()
                    }
                    pulsacion = false
                }
                "×" -> {
                    x = x * y
                    if ( exadec != -1 ) {
                        Resultado.text = x.toInt().toString(16)
                    } else {
                        Resultado.text = x.toString()
                    }
                    pulsacion = false
                }
                "÷" -> {
                    if (y == 0.0) {
                        ce(v)
                        Resultado.text = "Imposible÷0"
                    }
                    else{
                        x = x / y
                        if ( exadec != -1 ) {
                            Resultado.text = x.toInt().toString(16)
                        } else {
                            Resultado.text = x.toString()
                        }
                        pulsacion = false
                    }
                }
            }
        }
        signo = "="
        numeros = ""
    }
}