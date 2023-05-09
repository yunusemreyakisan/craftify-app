package com.morestudio.craftify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.morestudio.craftify.R
import com.morestudio.craftify.databinding.ActivityAddNoteBinding

class AddNote : AppCompatActivity() {
    lateinit var binding : ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}