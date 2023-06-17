package com.example.app.data.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recordatorio")
data class Recordatorio(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nombreU: String,
    val medicationType: String,
    val nombreM: String,
    val fechaI: String,
    val fechaF: String,
    val horaI: String,
    var gender: String,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean
)