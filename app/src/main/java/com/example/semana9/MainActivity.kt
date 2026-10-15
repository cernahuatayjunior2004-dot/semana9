package com.example.semana9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.semana9.ui.theme.Semana9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ControlAforo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ControlAforo(modifier: Modifier = Modifier) {
    // 1. Estados necesarios para controlar los datos
    var personasAdentro by remember { mutableStateOf(0) }
    var ultimoRegistrado by remember { mutableStateOf("Ninguno") }
    var nombreInput by remember { mutableStateOf("") }

    // Interfaz organizada en una Column vertical
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // --- 1. Título y Contadores (Text) ---
        Text(text = "Control de Aforo")
        Text(text = "Personas adentro: $personasAdentro")
        Text(text = "Último registrado: $ultimoRegistrado")

        // --- 2. Formulario de Registro (TextField y Button) ---
        TextField(
            value = nombreInput,
            onValueChange = { nombreInput = it },
            label = { Text("Nombre de la persona") }
        )

        Button(
            onClick = {
                if (nombreInput.isNotBlank()) {
                    ultimoRegistrado = nombreInput
                    personasAdentro++
                    nombreInput = "" // Limpia el campo de texto
                }
            }
        ) {
            Text("Registrar e Ingresar")
        }

        // --- 3. Fila de Acciones Rápidas (Row y Button) ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Botón Salida (-1)
            Button(
                onClick = {
                    // Restricción: No bajar de 0
                    if (personasAdentro > 0) {
                        personasAdentro--
                    }
                }
            ) {
                Text("Salida (-1)")
            }

            // Botón Reiniciar
            Button(
                onClick = {
                    personasAdentro = 0
                    ultimoRegistrado = "Ninguno"
                }
            ) {
                Text("Reiniciar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ControlAforoPreview() {
    Semana9Theme {
        ControlAforo()
    }
}

// --- Ejercicios anteriores ---

@Composable
fun BotonSaludo(modifier: Modifier = Modifier) {
    var mensaje by remember { mutableStateOf("Hola, presiona el botón") }

    Column(modifier = modifier) {
        Text(text = mensaje)
        Button(onClick = { mensaje = "¡Gracias por presionar el botón!" }) {
            Text("Presióname")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BotonSaludoPreview() {
    Semana9Theme {
        BotonSaludo()
    }
}

@Composable
fun ContadorClics(modifier: Modifier = Modifier) {
    var contador by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        Text(text = "Clics realizados: $contador")
        Button(onClick = { contador++ }) {
            Text("Incrementar")
        }
        Button(onClick = { contador = 0 }) {
            Text("Reiniciar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorClicsPreview() {
    Semana9Theme {
        ContadorClics()
    }
}

@Composable
fun ControlLuz(modifier: Modifier = Modifier) {
    var focoEncendido by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(text = if (focoEncendido) "Foco: ENCENDIDO" else "Foco: APAGADO")
        Row {
            Button(onClick = { focoEncendido = true }) {
                Text("Encender")
            }
            Button(onClick = { focoEncendido = false }) {
                Text("Apagar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ControlLuzPreview() {
    Semana9Theme {
        ControlLuz()
    }
}

@Composable
fun MiniRegistro(modifier: Modifier = Modifier) {
    var entradaTexto by remember { mutableStateOf("") }
    var nombreConfirmado by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = entradaTexto,
            onValueChange = { entradaTexto = it }
        )
        Button(onClick = { nombreConfirmado = entradaTexto }) {
            Text("Guardar")
        }
        Text(text = "Usuario registrado: $nombreConfirmado")
    }
}

@Preview(showBackground = true)
@Composable
fun MiniRegistroPreview() {
    Semana9Theme {
        MiniRegistro()
    }
}
