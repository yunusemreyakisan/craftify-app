package com.morestudio.craftify.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.morestudio.craftify.data.repository.NoteRepository
import com.morestudio.craftify.viewmodel.AddNoteViewModel

class AddNoteViewModelFactory(private val repository: NoteRepository)  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            return AddNoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
