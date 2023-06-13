package com.morestudio.craftify.data.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.morestudio.craftify.data.model.Note

@Dao
interface NoteDAO {

    //Get Notes Size
    @Query("SELECT COUNT(*) FROM note")
    fun getNoteCount(): Int

    //Get Notes
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note?>

    //Get Pinned Notes
    @Query("SELECT * FROM note WHERE isPinned = 1")
    fun getPinnedNotes(): List<Note?>

    //Get Pinned Notes Size
    @Query("SELECT COUNT(isPinned) FROM note")
    fun getPinnedNoteCount(): Int

    //Get Note by ID
    @Query("SELECT * FROM note WHERE noteId = :id")
    fun getNoteById(id: Long): Note

    //insertNote
    @Insert
    fun insertNote(vararg note: Note?)

    @Update
    fun updateNote(note: Note?)

    //deleteNote
    @Delete
    fun deleteNote(note: Note?)

    //DeleteById
    @Query("DELETE FROM note WHERE noteId = :noteId")
    suspend fun deleteById(noteId: Int)
}