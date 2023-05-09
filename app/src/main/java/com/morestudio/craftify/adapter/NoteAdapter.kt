package com.morestudio.craftify.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morestudio.craftify.R
import com.morestudio.craftify.databinding.NoteItemBinding
import com.morestudio.craftify.model.Note

class NoteAdapter(var notes: List<Note?>) : RecyclerView.Adapter<NoteAdapter.VH>() {

    //ViewHolder
    inner class VH(val view : NoteItemBinding) : RecyclerView.ViewHolder(view.root){
        fun bind(note : Note){
            view.title.text = note.title
            view.content.text = note.content
            view.createdAt.text = note.createdAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return VH(NoteItemBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val note = notes.get(position)
        holder.view.content.text = note?.content
        holder.view.title.text = note?.title
        holder.view.createdAt.text = note?.createdAt
    }


}