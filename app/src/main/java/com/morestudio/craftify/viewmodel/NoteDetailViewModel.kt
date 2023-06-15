package com.morestudio.craftify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.data.model.Note
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repository: NoteRepository) : ViewModel() {
    //Update
    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    //Insert
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
    //Delete
    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}