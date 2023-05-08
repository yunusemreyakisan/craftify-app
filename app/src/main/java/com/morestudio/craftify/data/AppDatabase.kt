package com.morestudio.craftify.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.morestudio.craftify.model.Note


@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO?

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDBInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "Ornek_DB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}