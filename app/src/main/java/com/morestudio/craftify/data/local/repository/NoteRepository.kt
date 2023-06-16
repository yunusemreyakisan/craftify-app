package com.morestudio.craftify.data.local.repository

import androidx.lifecycle.LiveData
import com.morestudio.craftify.data.local.dao.NoteDAO
import com.morestudio.craftify.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDAO) {

    fun getAllNotes(): MutableList<Note?> {
        return noteDao.getAllNotes()
    }

    fun getAllPinnedNotes(): MutableList<Note?> {
        return noteDao.getPinnedNotes()
    }

    fun getAllPinnedNotesSize(): Int? {
        return noteDao.getPinnedNoteCount()
    }

    fun getAllNotesSize(): Int? {
        return noteDao.getNoteCount()
    }

    //Get Note by Id
    fun getNoteById(id: Int): LiveData<Note> {
        return noteDao.getNoteById(id)
    }


    //INSERT
    suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    //UPDATE
    suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }

    //DELETE
    suspend fun delete(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }

}