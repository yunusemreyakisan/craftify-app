package com.morestudio.craftify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.data.model.Note
import kotlinx.coroutines.launch

class NoteFragmentViewModel(private val repository: NoteRepository) : ViewModel() {

    //Get All Notes
    fun getAllNotes(): List<Note?> {
        return repository.getAllNotes()
    }

    //Get All Notes Size
    suspend fun getAllNotesSize(): Int? {
        return repository.getAllNotesSize()
    }

    fun getNoteById(id: Int){
        viewModelScope.launch {
            repository.getNoteById(id)
        }
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


