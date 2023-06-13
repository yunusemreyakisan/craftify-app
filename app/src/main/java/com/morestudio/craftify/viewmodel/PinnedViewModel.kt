package com.morestudio.craftify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.data.model.Note
import kotlinx.coroutines.launch

class PinnedViewModel(private val repository: NoteRepository) : ViewModel() {

    //Get All Pinned Notes
    fun getAllPinnedNotes(): List<Note?> {
        return repository.getAllPinnedNotes()
    }

    //Get All Notes Size
    suspend fun getAllPinnedNotesSize(): Int? {
        return repository.getAllPinnedNotesSize()
    }

}