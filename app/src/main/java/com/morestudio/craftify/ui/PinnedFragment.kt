package com.morestudio.craftify.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.morestudio.craftify.adapter.NoteAdapter
import com.morestudio.craftify.data.local.database.NoteDatabase
import com.morestudio.craftify.data.local.repository.NoteRepository
import com.morestudio.craftify.databinding.FragmentPinnedBinding
import com.morestudio.craftify.data.model.Note
import com.morestudio.craftify.viewmodel.PinnedViewModel
import com.morestudio.craftify.viewmodel.factory.PinnedViewModelFactory
import kotlinx.coroutines.launch


class PinnedFragment : Fragment() {
    lateinit var binding : FragmentPinnedBinding
    lateinit var pinnedNotesAdapter : NoteAdapter
    private lateinit var viewModel: PinnedViewModel
    private var pinnedNotes : List<Note?> = ArrayList()
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

        // Create the view model using the factory
        val dataSource = context?.let { NoteDatabase.getDBInstance(it)?.noteDAO() }
        val repository = dataSource?.let { NoteRepository(it) }
        val viewModelFactory = repository?.let { PinnedViewModelFactory(it) }
        viewModel = viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            )[PinnedViewModel::class.java]
        }!!

        //Pinned Notes init
        pinnedNotes = viewModel.getAllPinnedNotes()

        //Notes size
        lifecycleScope.launch {
            pinnedNoteSize = viewModel.getAllPinnedNotesSize()
            Log.e("Pinned note size: ", pinnedNoteSize.toString())
        }


        if(pinnedNoteSize == 0){
            binding.notesSizeZeroLayout.visibility = View.VISIBLE
        }

        //Set adapter
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