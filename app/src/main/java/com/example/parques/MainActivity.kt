package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter


data class Review(val text: String, val author: String)

val reviews = listOf(
    Review("Excelente aplicación.", "Usuario A"),
    Review("Muy útil para principiantes.", "Usuario B"),
    Review("Necesita mejoras en la interfaz.", "Usuario C")
)

val categories = listOf(
    "Introducción a la Impresión 3D",
    "Modelado 3D",
    "Calibración de Impresoras",
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //MyJetpackComposeContent()
            //medialist()
            ParquesTablero()

        }
    }
}

@Composable
fun ParquesTablero() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),

            ) {
            repeat(20) { fila ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(20) { columna ->
                        val color = when {
                            //color casillas de las esquinas
                            fila in 0..6 && columna in 0..6 -> Color.Red
                            fila in 0..6 && columna in 13..19 -> Color.Blue
                            fila in 13..19 && columna in 0..6 -> Color.Green
                            fila in 13..19 && columna in 13..19 -> Color.Yellow

                            // color centro del tablero
                            fila in 8..11 && columna in 8..11 -> when {
                                fila <= 9 && columna <= 9 -> Color.Red
                                fila <= 9 && columna > 9 -> Color.Blue
                                fila > 9 && columna <= 9 -> Color.Green
                                else -> Color.Yellow
                            }

                            //casillas de victoria
                            fila in 1..7 && columna in 9..10 -> Color.Red

                            fila in 9..10 && columna in 11..18 -> Color.Blue

                            fila in 12..18 && columna in 9..10 -> Color.Yellow

                            fila in 9..10 && columna in 1..8 -> Color.Green

                            //casillas de seguro
                            fila in 0..0 && columna in 9..10 -> Color.White//roja

                            fila in 9..10 && columna in 19..19 -> Color.White//azul

                            fila in 19..19 && columna in 9..10 -> Color.White//amarilla

                            fila in 9..10 && columna in 0..0 -> Color.White//verde

                            //casillas de salida








                            else -> Color.Cyan
                        }
                        ParqueCelda(color)
                    }
                }
            }
        }
    }
}

@Composable
fun ParqueCelda(color: Color) {
    Box(
        modifier = Modifier
            .size(19.dp)
            .background(color)
    ) {
        // Contenido de la celda del parqués
    }
}

@Preview
@Composable
fun ParquesTableroPreview() {
    ParquesTablero()
}