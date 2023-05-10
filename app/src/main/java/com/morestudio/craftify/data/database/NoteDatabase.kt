package com.morestudio.craftify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.morestudio.craftify.data.dao.NoteDAO
import com.morestudio.craftify.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO?

    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getDBInstance(context: Context): NoteDatabase? {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "Note"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}