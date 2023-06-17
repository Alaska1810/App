package com.example.app.ui.screens

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.data.persistence.DatabaseRecordatorio
import com.example.app.data.persistence.Recordatorio
import com.example.app.data.repositories.RecordatorioRepository

class RecordatorioViewModel(application: Application): ViewModel() {

    private val recordatorioRepository: RecordatorioRepository
    val allRecordatorio: LiveData<List<Recordatorio>>

    init{
        val db_recordatorio = DatabaseRecordatorio.getInstance(
            application.applicationContext)

        recordatorioRepository = RecordatorioRepository(db_recordatorio.getDaoRecordatorio())
        allRecordatorio = recordatorioRepository.allRecordatorio
    }

    fun createRecordatorio(recordatorio: Recordatorio){
        recordatorioRepository.createRecordatorio(recordatorio)
    }

    fun updateRecordatorio(recordatorio: Recordatorio){
        recordatorioRepository.updateRecordatorio(recordatorio)
    }

    fun deleteRecordatorio(recordatorio: Recordatorio){
        recordatorioRepository.deleteRecordatorio(recordatorio)
    }
}

@Suppress("UNCHECKED_CAST")
class RecordatorioViewModelFactory(
    val application: Application)
    :ViewModelProvider.Factory{

    override fun <T : ViewModel>
            create(modelClass: Class<T>)
    : T {
        return RecordatorioViewModel(
            application = application) as T
    }
}