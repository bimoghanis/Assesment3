package com.pt2.assesment3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CatatanEntity::class], version = 1, exportSchema = false)
abstract class CatatanDb : RoomDatabase() {

    abstract fun catatanDao(): CatatanDao

    companion object {
        @Volatile
        private var INSTANCE: CatatanDb? = null

        fun getDatabase(context: Context): CatatanDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatatanDb::class.java,
                    "catatan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
