package com.morestudio.craftify.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.morestudio.craftify.adapter.NoteAdapter
import com.morestudio.craftify.data.local.database.NoteDatabase
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.databinding.FragmentNoteBinding
import com.morestudio.craftify.data.model.Note
import com.morestudio.craftify.viewmodel.NoteFragmentViewModel
import com.morestudio.craftify.viewmodel.factory.NoteViewModelFactory
import kotlinx.coroutines.launch


class NoteFragment : Fragment()  {
    lateinit var notesAdapter : NoteAdapter
    lateinit var binding : FragmentNoteBinding
    private var notes : List<Note?> = ArrayList()
    private lateinit var viewModel: NoteFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)

        // Create the view model using the factory
        val dataSource = context?.let { NoteDatabase.getDBInstance(it)?.noteDAO() }
        val repository = dataSource?.let { NoteRepository(it) }
        val viewModelFactory = repository?.let { NoteViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            )[NoteFragmentViewModel::class.java]
        }!!

        //Get All notes
        notes = viewModel.getAllNotes()

        //set Recyclerview
        setRecyclerAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //Note Size Update UI
        lifecycleScope.launch {
            val noteSize = viewModel.getAllNotesSize()
            Log.e("Notes Size: ", noteSize.toString())
            if (noteSize == 0) {
                binding.notesSizeZeroLayout.visibility = View.VISIBLE
            } else {
                binding.notesSizeZeroLayout.visibility = View.GONE
            }
        }






    }

    //Set Adapter
    fun setRecyclerAdapter(){
        notesAdapter = NoteAdapter(notes)
        binding.notesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRecyclerView.adapter = notesAdapter
    }



}


