package com.morestudio.craftify.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.viewmodel.PinnedViewModel

class PinnedViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PinnedViewModel::class.java)) {
            return PinnedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
