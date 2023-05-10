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

    @Query("SELECT * FROM note WHERE isPinned = 1")
    fun getPinnedNotes(): List<Note?>

    @Query("SELECT COUNT(isPinned) FROM note")
    fun getPinnedNoteCount(): Int

    //insertNote
    @Insert
    fun insertNote(vararg note: Note?)

    @Update
    fun updateNote(note: Note?)

    //deleteNote
    @Delete
    fun deleteNote(note: Note?)
}