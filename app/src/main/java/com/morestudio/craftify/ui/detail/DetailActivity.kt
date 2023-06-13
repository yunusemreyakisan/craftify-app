package com.morestudio.craftify.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.morestudio.craftify.MainActivity
import com.morestudio.craftify.R
import com.morestudio.craftify.data.local.database.NoteDatabase
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.data.model.Note
import com.morestudio.craftify.databinding.ActivityDetailBinding
import com.morestudio.craftify.viewmodel.AddNoteViewModel
import com.morestudio.craftify.viewmodel.factory.AddNoteViewModelFactory

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewmodel
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

        //get intent data
        getAndSetIntentData()

        //TODO: Delete by id eklenecek.



    }


    //get Intent data
    fun getAndSetIntentData() {
        val position = intent.getIntExtra("position", -1)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val createdAt = intent.getStringExtra("createdAt")
        val isPinned = intent.getIntExtra("isPinned", 0)

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


