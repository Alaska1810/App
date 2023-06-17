package com.example.app.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.CaseMap.Title
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import com.example.app.R
import com.example.app.data.persistence.Recordatorio
import com.example.app.ui.navigation.BottomBar
import com.example.app.ui.navigation.Screens
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Agregar(navHost: NavHostController,
            viewModel: RecordatorioViewModel
) {
    var medicationType by remember { mutableStateOf("") }
    var nombreU by remember { mutableStateOf("") }
    var nombreM by remember { mutableStateOf("") }
    val (focusNombreU, focusNombreM) = remember { FocusRequester.createRefs() }
    var dosageDates by remember { mutableStateOf(LocalDate.now()) }
    var dosageTime by remember { mutableStateOf(LocalTime.NOON) }
    val formattedDate by remember {
        derivedStateOf { DateTimeFormatter.ofPattern("MMM dd yyyy").format(dosageDates) }
    }
    val formattedTime by remember {
        derivedStateOf { DateTimeFormatter.ofPattern("hh:mm").format(dosageTime) }
    }
    
    val scrollState = rememberScrollState()



    Scaffold(
        bottomBar = {
            BottomBar(navHost = navHost)
        }
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.20f),
                    Alignment.TopEnd,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.agrega),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Column(
                Modifier
                    .padding(38.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Paciente", fontSize = 17.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(value = nombreU, onValueChange = { nombreU = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusNombreU),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    singleLine = true,
                    label = { Text(text = "Nombre del paciente", fontSize = 15.sp) }
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    "Selecciona el tipo de medicamento", fontSize = 17.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                MedicationTypeSelection(
                    selectedType = medicationType,
                    onSelectionChanged = { medicationType = it })

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Medicamento", fontSize = 17.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(value = nombreM, onValueChange = { nombreM = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusNombreM),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    singleLine = true,
                    label = { Text(text = "Nombre del medicamento", fontSize = 15.sp) }
                )
                Spacer(Modifier.height(25.dp))
                Text(
                    "Selecciona la fecha y hora de dosificación", fontSize = 17.sp,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                //MiDatePicker()
                var fechaI by rememberSaveable { mutableStateOf("") }
                val anio: Int
                val mes: Int
                val dia: Int
                val mCalendar: Calendar = Calendar.getInstance()
                anio = mCalendar.get(Calendar.YEAR)
                mes = mCalendar.get(Calendar.MONTH)
                dia = mCalendar.get(Calendar.DAY_OF_MONTH)

                val mDatePickerDialog = DatePickerDialog(
                    LocalContext.current,
                    { Datepicker, anio: Int, mes: Int, dia: Int ->
                        fechaI = "$dia/${mes + 1}/$anio"
                    }, anio, mes, dia
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row() {
                        OutlinedTextField(
                            value = fechaI,
                            onValueChange = { fechaI = it },
                            readOnly = true,
                            label = {
                                Text(
                                    text = "Selecciona la fecha de inicio",
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .clickable { mDatePickerDialog.show() }
                        )
                    }
                }
                Spacer(Modifier.height(25.dp))
                //MiDatePicker1()
                    var fechaF by rememberSaveable { mutableStateOf("") }
                    val anio1: Int
                    val mes1: Int
                    val dia1: Int
                    val mCalendar1: Calendar = Calendar.getInstance()
                    anio1 = mCalendar1.get(Calendar.YEAR)
                    mes1 = mCalendar1.get(Calendar.MONTH)
                    dia1 = mCalendar1.get(Calendar.DAY_OF_MONTH)

                    val mDatePickerDialog1 = DatePickerDialog(
                        LocalContext.current,
                        { Datepicker, anio1: Int, mes1: Int, dia1: Int ->
                            fechaF = "$dia1/${mes1 + 1}/$anio1"
                        }, anio1, mes1, dia1
                    )
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row() {
                            OutlinedTextField(
                                value = fechaF,
                                onValueChange = { fechaF = it },
                                readOnly = true,
                                label = {
                                    Text(
                                        text = "Selecciona la fecha final",
                                        fontSize = 15.sp
                                    )
                                }
                            )
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(4.dp)
                                    .clickable { mDatePickerDialog1.show() }
                            )
                        }
                    }
                Spacer(Modifier.height(25.dp))
                //ShowTimePicker()
                    var horaI by rememberSaveable { mutableStateOf("") }
                    val hour: Int
                    val minute: Int
                    val mcalendar2: Calendar = Calendar.getInstance()
                    hour = mcalendar2.get(Calendar.HOUR_OF_DAY)
                    minute = mcalendar2.get(Calendar.MINUTE)

                    val mtimePickerDialog1 = TimePickerDialog(
                        LocalContext.current,
                        { TimePicker, hour: Int, minute: Int ->
                            horaI = "$hour:$minute"
                        }, hour, minute, false
                    )
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row() {
                        OutlinedTextField(
                            value = horaI,
                            onValueChange = { horaI = it },
                            readOnly = true,
                            label = {
                                Text(
                                    text = "Selecciona la hora inicial: ",
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Icon(
                            imageVector = Icons.Filled.AvTimer,
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .clickable { mtimePickerDialog1.show() }
                        )
                    }
                }
                Spacer(Modifier.height(15.dp))
                //ShowTimePicker1()
                var gender by rememberSaveable { mutableStateOf("") }
                val hour1: Int
                val minute1: Int
                val mcalendar3: Calendar = Calendar.getInstance()
                hour1 = mcalendar3.get(Calendar.HOUR_OF_DAY)
                minute1 = mcalendar3.get(Calendar.MINUTE)

                val mtimePickerDialog2 = TimePickerDialog(
                    LocalContext.current,
                    { TimePicker, hour1: Int, minute1: Int ->
                        gender = "$hour1:$minute1"
                    }, hour1, minute1, false
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row() {
                        OutlinedTextField(
                            value = gender,
                            onValueChange = { gender = it },
                            readOnly = true,
                            label = {
                                Text(
                                    text = "¿Cada cuantas horas?: ",
                                    fontSize = 15.sp
                                )
                            }
                        )
                        Icon(
                            imageVector = Icons.Filled.AvTimer,
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .clickable { mtimePickerDialog2.show() }
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))

                    Column(modifier = Modifier
                        .padding(18.dp)
                        .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        val context = LocalContext.current
                        val idCanal = "Canal Recordatorio"
                        val idNotificacion = 0

                        LaunchedEffect(Unit){
                            crearCanalNotificacion(idCanal, context)
                        }
                        Spacer(modifier = Modifier.height(35.dp))
                        Button(
                            enabled = nombreU.isNotEmpty()&&medicationType.isNotEmpty()&&nombreM.isNotEmpty()&&fechaI.isNotEmpty()&&fechaF.isNotEmpty()&&horaI.isNotEmpty()&&gender.isNotEmpty(),
                            onClick = {
                                    val recordatorio: Recordatorio = Recordatorio(
                                        id = 0,
                                        nombreU = nombreU,
                                        medicationType = medicationType,
                                        nombreM = nombreM,
                                        fechaI = fechaI,
                                        fechaF = fechaF,
                                        horaI = horaI,
                                        gender = gender,
                                        isCompleted = false
                                    )
                                    viewModel.createRecordatorio(recordatorio)
                                    navHost.navigate(Screens.Recordatorio.route)
                                notificacionBasica(
                                    context,
                                    idCanal,
                                    idNotificacion,
                                    "Recordatorio",
                                    "No olvides tomar tu medicamento"
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(50.dp)
                        ) {
                            Text("Guardar")
                        }
                    }
                Spacer(Modifier.height(35.dp))
            }
        }
    }
}


//@SuppressLint("MissingPermission")
@SuppressLint("MissingPermission")
fun notificacionBasica(
    context: Context,
    idCanal: String,
    idNotificacion: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context,idCanal)
        .setSmallIcon(R.drawable.notificacion)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)){
        notify(idNotificacion, builder.build())
    }
}

fun crearCanalNotificacion(
    idCanal: String,
    context: Context
    ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var CHANNEL_ID: String
            val nombre = "CanalRecordatorio"
            val descripcion = "Canal de notificaciones Recordatorio"
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(idCanal, nombre, importancia)
                .apply {
                    description = descripcion
                }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as
                        NotificationManager
            notificationManager.createNotificationChannel(canal)
        }
}


@Composable
fun MedicationTypeSelection(selectedType: String, onSelectionChanged: (String) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        listOf("Píldora", "Jarabe", "Inyección" /*"Topical"*/).forEach { type ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = (type == selectedType), onClick = { onSelectionChanged(type) })
                Text(type, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

/*@Composable
fun Notificaciones(){
    val context = LocalContext.current
    val idCanal = "Canal Recordatorio"
    val idNotificacion = 0

    LaunchedEffect(Unit){
        crearCanalNotificacion(idCanal, context)
    }
    Column() {
        Spacer(modifier = Modifier.height(55.dp))
        Button(onClick = {
                         notificacionProgramada(context)
            navController.navigate(Screens.Recordatorio.name)
        },
            modifier =  Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(text = "Guardar")
        }
    }
}*/

/*fun crearCanalNotificacion(
    idCanal: String,
    context: Context
){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        var CHANNEL_ID: String
        val nombre = "CanalRecordatorio"
        val descripcion = "Canal de notificaciones Recordatorio"
        val importancia = NotificationManager.IMPORTANCE_DEFAULT
        val canal = NotificationChannel(idCanal, nombre, importancia)
            .apply {
                description = descripcion
            }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as
                    NotificationManager
        notificationManager.createNotificationChannel(canal)
    }
}*/

