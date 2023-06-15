package com.morestudio.craftify.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.viewmodel.AddNoteViewModel
import com.morestudio.craftify.viewmodel.NoteDetailViewModel

class NoteDetailViewModelFactory(private val repository: NoteRepository)  : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}