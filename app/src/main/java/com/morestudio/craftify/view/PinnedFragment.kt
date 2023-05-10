package com.morestudio.craftify.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.morestudio.craftify.R
import com.morestudio.craftify.adapter.NoteAdapter
import com.morestudio.craftify.data.NoteDatabase
import com.morestudio.craftify.databinding.FragmentPinnedBinding
import com.morestudio.craftify.model.Note


class PinnedFragment : Fragment() {

    lateinit var binding : FragmentPinnedBinding
    lateinit var pinnedNotesAdapter : NoteAdapter
    private var pinnedNotes : List<Note?> = ArrayList()
    lateinit var database : NoteDatabase
    var pinnedNoteSize : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPinnedBinding.inflate(inflater, container, false)


        database  = NoteDatabase.getDBInstance(requireContext())!!
        Log.e("Notes: ", database.noteDAO()?.getAllNotes().toString())

        pinnedNotes = database.noteDAO()?.getPinnedNotes()!!

        //Notes size
        pinnedNoteSize = database.noteDAO()?.getPinnedNoteCount()
            ?.let { Integer.valueOf(it)}

        if(pinnedNoteSize == 0){
            binding.notesSizeZeroLayout.visibility = View.VISIBLE
        }

        setRecyclerAdapter()



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    //Set Adapter
    fun setRecyclerAdapter(){
        pinnedNotesAdapter = NoteAdapter(pinnedNotes)
        binding.pinnedNotesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.pinnedNotesRecyclerView.adapter = pinnedNotesAdapter
    }


}