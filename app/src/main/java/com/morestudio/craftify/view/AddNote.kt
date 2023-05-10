package com.morestudio.craftify.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
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
    var isPinned : Boolean = false

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


        val window = this.window
        if(Helpers.isDarkThemeEnabled(this)){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.md_theme_dark_background)
        }else{
            window.statusBarColor = this.resources.getColor(R.color.md_theme_light_background)
        }

        binding.isPinnedAddNote.setOnClickListener {
            togglePin()
        }


    }


    fun togglePin() {
        isPinned = !isPinned // pinli durumun tersine çevir
        if (isPinned) {
            binding.isPinnedAddNote.setImageResource(R.drawable.true_pinned)
        } else {
            binding.isPinnedAddNote.setImageResource(R.drawable.flag)
        }
    }



    fun saveNewNote(){
        val title = binding.txtTitle.text.toString()
        val content = binding.txtContent.text.toString()
        val createdAt = Helpers.olusturmaZamaniniGetir()


        if(isFieldEmpty(title, content, createdAt)){
            Toast.makeText(this, "Lütfen fikrinizi giriniz", Toast.LENGTH_SHORT).show()
        }else{
            val note  = database.noteDAO()
                ?.insertNote(
                    Note( title = title, content =  content, createdAt = createdAt, isPinned = isPinned)
                )
            Toast.makeText(this, "Fikriniz kaydedildi", Toast.LENGTH_SHORT).show()

            Helpers.going(this, MainActivity::class.java)
        }

    }



}