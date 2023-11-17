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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.media.MediaPlayer


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParquesTablero()

        }
    }
}


data class Position(val fila: Int, val columna: Int)
@Composable
fun ParquesTablero() {

    var jugadorPosicion by remember { mutableStateOf(Position(0, 0)) }
    var resultadoDados by remember { mutableStateOf(0) }

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
                            fila in 3..3 && columna in 3..19 -> Color.White//arriba
                            fila in 3..19 && columna in 3..3 -> Color.White//izquierda
                            fila in 3..19 && columna in 16..16 -> Color.White//verde
                            fila in 16..16 && columna in 3..19 -> Color.White//verde







                            else -> Color.Cyan
                        }

                        ParqueCelda(color)


                    }
                }
            }
        }


        Button(
            onClick = {
                resultadoDados = (1..6).random()
                jugadorPosicion = moverJugador(jugadorPosicion, resultadoDados)
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Lanzar Dados")
        }


    }


}

@Composable
fun ParqueCelda(color: Color) {
    Box(

        modifier = Modifier
            .size(19.8.dp)
            .background(color)
    ) {
        // Contenido de la celda del parqués

    }
}


fun moverJugador(posicionActual: Position, pasos: Int): Position {
    // Lógica para mover al jugador según el resultado de los dados
    // Puedes adaptar esto según las reglas específicas de tu juego
    val nuevaFila = (posicionActual.fila + pasos) % 20
    val nuevaColumna = (posicionActual.columna + pasos) % 20
    return Position(nuevaFila, nuevaColumna)
}

/**
@Composable
fun DiceButton() {
    val context = LocalContext.current
    val diceSound = remember { MediaPlayer.create(context, R.raw.dice_roll) }

    // Limpia los recursos cuando el composable ya no está en el árbol de composición
    LaunchedEffect(Unit) {
        onDispose {
            if (diceSound.isPlaying) {
                diceSound.stop()
            }
            diceSound.release()
        }
    }

    Button(onClick = {
        if(diceSound.isPlaying) {
            diceSound.stop()
        }
        diceSound.start()
    }) {
        Text(text = "Lanzar dado")
    }
}

*/

@Preview
@Composable
fun ParquesTableroPreview() {
    ParquesTablero()
}

