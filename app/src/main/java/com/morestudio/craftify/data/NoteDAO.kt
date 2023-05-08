package com.morestudio.craftify.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.morestudio.craftify.model.Note

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note?>?

    //insertNote methodumuz ile icerisine birden fazla arguman atayabileyecegimizi soyluyoruz.
    @Insert
    fun insertNote(vararg note: Note?)

    //deleteNote methodu ile not silinmesi saglanacak.
    @Delete
    fun deleteNote(note: Note?)
}