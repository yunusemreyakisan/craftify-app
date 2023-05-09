package com.morestudio.craftify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.morestudio.craftify.model.Note

@Dao
interface NoteDAO {
    @Query("SELECT COUNT(*) FROM note")
    fun getNoteCount(): Int
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note?>

    //insertNote methodumuz ile icerisine birden fazla arguman atayabileyecegimizi soyluyoruz.
    @Insert
    fun insertNote(vararg note: Note?)

    @Update
    fun updateNote(note: Note?)

    //deleteNote methodu ile not silinmesi saglanacak.
    @Delete
    fun deleteNote(note: Note?)
}