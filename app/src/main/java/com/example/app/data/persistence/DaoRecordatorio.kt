package com.example.app.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoRecordatorio {

    @Query("SELECT * FROM recordatorio")
    fun getAllRecordatorio(): LiveData<List<Recordatorio>>

    @Insert
    fun insert(vararg recordatorio: Recordatorio)

    @Update
    fun update(vararg recordatorio: Recordatorio)

    @Delete
    fun delete(recordatorio: Recordatorio)

}