package com.morestudio.craftify.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.morestudio.craftify.adapter.NoteAdapter
import com.morestudio.craftify.data.NoteDatabase
import com.morestudio.craftify.databinding.FragmentNoteBinding
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.model.Note


class NoteFragment : Fragment() {
    lateinit var notesAdapter : NoteAdapter
    lateinit var binding : FragmentNoteBinding
    private var notes : List<Note?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)

        val database  = NoteDatabase.getDBInstance(requireContext())!!
        Log.e("Notes: ", database.noteDAO()?.getAllNotes().toString())
        /*val note  = database.noteDAO()
            ?.insertNote(
                Note( title = "sdıuchgbvsudyvucıusdyvc bıusdvuchbsdıucbsdc", content =  "ıduskbfcsduıcbıusdhcouısgvdbucıkjbsdıvychjkbsdoıuvcj jsbkducvyhjsdbkcıuvyhjds bkcuvyhjds bkbcıuvyh djs", createdAt = Helpers.olusturmaZamaniniGetir(), isPinned = false))

         */
        Log.e("Notes: ", database.noteDAO()?.getAllNotes().toString())

        notes = database.noteDAO()?.getAllNotes()!!


        var noteSize = NoteDatabase.getDBInstance(requireContext())?.noteDAO()?.getNoteCount()
            ?.let { Integer.valueOf(it)}

        if(noteSize == 0){
            binding.notesSizeZeroLayout.visibility = View.VISIBLE
        }

        //set Recyclerview
        setRecyclerAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    //Set Adapter
    fun setRecyclerAdapter(){
        notesAdapter = NoteAdapter(notes)
        binding.notesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRecyclerView.adapter = notesAdapter
    }

}