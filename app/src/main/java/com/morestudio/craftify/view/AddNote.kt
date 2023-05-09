package com.morestudio.craftify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.morestudio.craftify.MainActivity
import com.morestudio.craftify.R
import com.morestudio.craftify.data.NoteDatabase
import com.morestudio.craftify.databinding.ActivityAddNoteBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.helpers.Helpers.isFieldEmpty
import com.morestudio.craftify.model.Note

class AddNote : AppCompatActivity() {
    lateinit var binding : ActivityAddNoteBinding
    lateinit var database : NoteDatabase
    var noteSize : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DB Instance
        database  = NoteDatabase.getDBInstance(applicationContext)!!
        Log.e("Notes: ", database.noteDAO()?.getAllNotes().toString())


        binding.olusturulmaTarihiAddNote.text = Helpers.olusturmaZamaniniGetir()
        binding.fabClose.setOnClickListener {
            super.onBackPressed()
        }


        binding.isPinnedAddNote.setOnClickListener {

        }


        binding.extendedFab.setOnClickListener {
            saveNewNote()
        }
    }



    fun saveNewNote(){
        val title = binding.txtTitle.text.toString()
        val content = binding.txtContent.text.toString()
        val createdAt = Helpers.olusturmaZamaniniGetir()
        var isPinned = false


        if(isFieldEmpty(title, content, createdAt)){
            Toast.makeText(this, "Lütfen fikrinizi giriniz", Toast.LENGTH_SHORT).show()
        }else{
            val note  = database.noteDAO()
                ?.insertNote(
                    Note( title = title, content =  content, createdAt = createdAt, isPinned = false)
                )
            Toast.makeText(this, "Fikriniz kaydedildi", Toast.LENGTH_SHORT).show()

            Helpers.going(this, MainActivity::class.java)
        }

    }



}