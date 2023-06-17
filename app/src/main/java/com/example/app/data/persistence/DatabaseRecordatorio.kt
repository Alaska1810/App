package com.example.app.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recordatorio::class], version = 1)
abstract class DatabaseRecordatorio: RoomDatabase() {
    abstract fun getDaoRecordatorio(): DaoRecordatorio

    companion object{
        private var INSTANCE: DatabaseRecordatorio? = null

        fun getInstance(context: Context): DatabaseRecordatorio {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room
                        .databaseBuilder(
                            context = context,
                            klass = DatabaseRecordatorio::class.java,
                            name = "db_recordatorio"
                        ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}