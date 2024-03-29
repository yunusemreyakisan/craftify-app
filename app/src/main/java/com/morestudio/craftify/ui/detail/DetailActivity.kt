package com.morestudio.craftify.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.morestudio.craftify.MainActivity
import com.morestudio.craftify.R
import com.morestudio.craftify.data.local.database.NoteDatabase
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.data.model.Note
import com.morestudio.craftify.databinding.ActivityDetailBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.viewmodel.AddNoteViewModel
import com.morestudio.craftify.viewmodel.NoteDetailViewModel
import com.morestudio.craftify.viewmodel.factory.AddNoteViewModelFactory
import com.morestudio.craftify.viewmodel.factory.NoteDetailViewModelFactory

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: NoteDetailViewModel
    var isPinnedBool: Boolean = false //Pinned State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get
        val position = intent.getIntExtra("position", -1)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val createdAt = intent.getStringExtra("createdAt")
        val isPinned = intent.getIntExtra("isPinned", 0)
        val id = intent.getIntExtra("id", 0)


        Log.e("id", id.toString())
        //get intent data
        getAndSetIntentData(position, title, content, createdAt, isPinned)

        // Create the view model using the factory
        val dataSource = applicationContext?.let { NoteDatabase.getDBInstance(it)?.noteDAO() }
        val repository = dataSource?.let { NoteRepository(it) }
        val viewModelFactory = repository?.let { NoteDetailViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            )[NoteDetailViewModel::class.java]
        }!!

        //TODO: Güncelleme ve silme islemleri yap.
        //Pinned
        binding.isPinnedDetailNote.setOnClickListener {
            togglePin(isPinnedBool)
        }

        binding.updateExtendedFab.setOnClickListener {
            updateNote(Note(title, content, createdAt, togglePin(isPinnedBool)))
        }

    }


    // Notu güncelleme
    private fun updateNote(note: Note) {
        val updatedTitle = binding.tvDetailTitle.text.toString()
        val updatedContent = binding.tvDetailContent.text.toString()
        val updatedCreatedAt = Helpers.olusturmaZamaniniGetir()
        val isPinned = togglePin(isPinnedBool)

        if (Helpers.isFieldEmpty(updatedTitle, updatedContent, updatedCreatedAt)) {
            Toast.makeText(this, getString(R.string.lutfen_fikrinizi_giriniz), Toast.LENGTH_SHORT)
                .show()
        } else {
            val updatedNote = Note(
                updatedTitle,
                updatedContent,
                updatedCreatedAt,
                isPinned
            )
            viewModel.update(updatedNote)
            Toast.makeText(this, getString(R.string.fikriniz_guncellendi), Toast.LENGTH_SHORT)
                .show()
            Helpers.going(this@DetailActivity, MainActivity::class.java)
        }
    }


    //Pinned Func
    private fun togglePin(isPinned: Boolean): Boolean {
        this.isPinnedBool = !isPinned // pinli durumun tersine çevir
        if (isPinned) {
            binding.isPinnedDetailNote.setImageResource(R.drawable.true_pinned)
            return true
        } else {
            binding.isPinnedDetailNote.setImageResource(R.drawable.flag)
            return false
        }
    }


    //get Intent data
    fun getAndSetIntentData(
        position: Int,
        title: String?,
        content: String?,
        createdAt: String?,
        isPinned: Int
    ) {
        if (position != -1 && title != null && content != null && createdAt != null) {
            // Pozisyon ve içerik bilgisi kullanimi
            Log.d("Position", "DetailActivity: $position")
            Log.d("Position", "DetailActivity: $isPinned")
            binding.tvDetailTitle.text = Editable.Factory.getInstance().newEditable(title)
            binding.tvDetailContent.text = Editable.Factory.getInstance().newEditable(content)
            binding.olusturulmaTarihiDetailNote.text = createdAt

            if (isPinned == View.VISIBLE) {
                binding.isPinnedDetailNote.setImageResource(R.drawable.true_pinned)
            } else {
                binding.isPinnedDetailNote.setImageResource(R.drawable.flag)
            }
        }

    }
}


