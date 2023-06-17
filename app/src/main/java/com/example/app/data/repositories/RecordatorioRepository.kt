package com.example.app.data.repositories

import androidx.lifecycle.LiveData
import com.example.app.data.persistence.DaoRecordatorio
import com.example.app.data.persistence.Recordatorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordatorioRepository(private val daoRecordatorio: DaoRecordatorio) {

    private val coroutineScope =
        CoroutineScope(Dispatchers.Main)

    val allRecordatorio: LiveData<List<Recordatorio>>
        = daoRecordatorio.getAllRecordatorio()

    fun createRecordatorio(recordatorio: Recordatorio){
        coroutineScope.launch(Dispatchers.IO) {
            daoRecordatorio.insert(recordatorio)
        }
    }

    fun updateRecordatorio(recordatorio: Recordatorio){
        coroutineScope.launch(Dispatchers.IO) {
            daoRecordatorio.update(recordatorio)
        }
    }

    fun deleteRecordatorio(recordatorio: Recordatorio){
        coroutineScope.launch(Dispatchers.IO) {
            daoRecordatorio.delete(recordatorio)
        }
    }
}