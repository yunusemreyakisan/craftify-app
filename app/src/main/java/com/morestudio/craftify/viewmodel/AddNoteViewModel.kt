package com.morestudio.craftify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.data.model.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    //Insert
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

}