package com.example.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.app.R
import com.example.app.data.persistence.Recordatorio
import com.example.app.ui.navigation.BottomBar
import com.example.app.ui.theme.AppTheme
import java.nio.file.Files.delete

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Recordatorio1(navHost: NavHostController, viewModel: RecordatorioViewModel) {
    val listRecordatorio: List<Recordatorio> by
    viewModel.allRecordatorio.observeAsState(listOf())

    Scaffold(
        bottomBar = {
            BottomBar(navHost = navHost)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.10f),
                Alignment.TopEnd,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.registro), contentDescription = "",
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Mi lista", fontSize = 25.sp,
                    style = MaterialTheme.typography.titleMedium
                )

            }
            LazyColumn(modifier =
            Modifier
                .padding(horizontal = 10.dp, vertical = 22.dp)
                .fillMaxSize()
            ){
                items(listRecordatorio){ item ->
                    ItemRecordatorio1(item, viewModel)
                    Spacer(modifier = Modifier.height(30.dp))
                }

            }
        }
    }
}
@Composable
fun ItemRecordatorio1(item: Recordatorio, viewModel: RecordatorioViewModel){

    Spacer(modifier = Modifier.height(10.dp))
    ElevatedCard(modifier = Modifier
        .padding(horizontal = 40.dp, vertical = 5.dp)
        .fillMaxWidth()
        .height(190.dp),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Tarea realizada"
            )
            Text(
                text = "Nombre del paciente: " + item.nombreU,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Text(
                text = "Tipo de medicamento: " + item.medicationType,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Text(
                text = "Nombre del medicamento: " + item.nombreM,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Text(
                text = "Fecha de Inicio: " + item.fechaI,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Text(
                text = "Fecha final: " + item.fechaF,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Text(
                text = "Hora de inicio: " + item.horaI + " hrs",
                style = MaterialTheme.typography.titleSmall
            )
        }
        Row {
            Text(
                text = "¿Cada cuantas horas?: " + item.gender + " hrs",
                style = MaterialTheme.typography.titleSmall)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.End, verticalAlignment = Alignment.CenterVertically
            ){
            IconButton(onClick = {
                viewModel.deleteRecordatorio(item)
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemRecordatorioPreview(){
    Spacer(modifier = Modifier.height(6.dp))
    ItemRecordatorio1(
        Recordatorio(
            id = 0, nombreU = "", medicationType = "",
    nombreM = "", fechaI = "", fechaF = "", horaI = "", gender = "", isCompleted = false),
        viewModel = viewModel()
    )
}



