package com.morestudio.craftify.data.repository

import androidx.lifecycle.LiveData
import com.morestudio.craftify.data.dao.NoteDAO
import com.morestudio.craftify.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDAO) {

    fun getAllNotes(): List<Note?> {
        return noteDao.getAllNotes()
    }

    fun getAllPinnedNotes(): List<Note?> {
        return noteDao.getPinnedNotes()
    }

    fun getAllPinnedNotesSize(): Int? {
        return noteDao.getPinnedNoteCount()
    }

    fun getAllNotesSize(): Int? {
        return noteDao.getNoteCount()
    }

    fun getNoteById(id: Long): Note {
        return noteDao.getNoteById(id)
    }

    suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note)
        }
    }

    suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(note)
        }
    }

    suspend fun delete(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }


}