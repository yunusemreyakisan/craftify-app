package com.morestudio.craftify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morestudio.craftify.data.repository.NoteRepository
import com.morestudio.craftify.model.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    //Get All Notes
    fun getAllNotes(): List<Note?> {
        return repository.getAllNotes()
    }

    //Get All Notes Size
    suspend fun getAllNotesSize(): Int? {
        return repository.getAllNotesSize()
    }

    //Get Note by id
    suspend fun getNoteById(id: Long): Note {
        return repository.getNoteById(id)
    }

    //Insert
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    //Update
    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    //Delete
    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

}