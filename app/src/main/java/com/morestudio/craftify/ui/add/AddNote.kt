package com.morestudio.craftify.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.morestudio.craftify.MainActivity
import com.morestudio.craftify.R
import com.morestudio.craftify.data.local.database.NoteDatabase
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.databinding.ActivityAddNoteBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.helpers.Helpers.isFieldEmpty
import com.morestudio.craftify.data.model.Note
import com.morestudio.craftify.viewmodel.AddNoteViewModel
import com.morestudio.craftify.viewmodel.factory.AddNoteViewModelFactory

class AddNote : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding
    lateinit var viewModel: AddNoteViewModel
    var createdAt = Helpers.olusturmaZamaniniGetir() //get createdAt time
    var isPinned: Boolean = false //Pinned State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the view model using the factory
        val dataSource = applicationContext?.let { NoteDatabase.getDBInstance(it)?.noteDAO() }
        val repository = dataSource?.let { NoteRepository(it) }
        val viewModelFactory = repository?.let { AddNoteViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            )[AddNoteViewModel::class.java]
        }!!


        //Getting createdAt time
        binding.olusturulmaTarihiAddNote.text = createdAt
        binding.fabClose.setOnClickListener {
            super.onBackPressed()
        }

        //FAB Listener
        binding.extendedFab.setOnClickListener {
            saveNewNote(createdAt)
        }

        //Update Theme
        updateTheme()

        //Pinned
        binding.isPinnedAddNote.setOnClickListener {
            togglePin()
        }


    }


    //Pinned Func
    private fun togglePin(): Boolean {
        isPinned = !isPinned // pinli durumun tersine çevir
        if (isPinned) {
            binding.isPinnedAddNote.setImageResource(R.drawable.true_pinned)
            return true
        } else {
            binding.isPinnedAddNote.setImageResource(R.drawable.flag)
            return false
        }
    }


    //Update Theme
    fun updateTheme() {
        val window = this.window
        if (Helpers.isDarkThemeEnabled(this)) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.md_theme_dark_background)
        } else {
            window.statusBarColor = this.resources.getColor(R.color.md_theme_light_background)
        }
    }


    //Save new note
    fun saveNewNote(createdAt: String) {
        val title = binding.txtTitle.text.toString()
        val content = binding.txtContent.text.toString()
        val createdAt = createdAt

        if (isFieldEmpty(title, content, createdAt)) {
            Toast.makeText(this, "Lütfen fikrinizi giriniz", Toast.LENGTH_SHORT).show()
        } else {
            togglePin() // isPinned degerinin guncellenmesi
            viewModel.insert(
                Note(
                    title = title,
                    content = content,
                    createdAt = createdAt,
                    isPinned = togglePin()
                )
            )
            Toast.makeText(this, "Fikriniz kaydedildi", Toast.LENGTH_SHORT).show()
            //Going Notes page
            Helpers.going(this, MainActivity::class.java)
        }

    }


}