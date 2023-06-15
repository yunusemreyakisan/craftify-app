package com.morestudio.craftify.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morestudio.craftify.R
import com.morestudio.craftify.databinding.NoteItemBinding
import com.morestudio.craftify.data.model.Note
import com.morestudio.craftify.helpers.Helpers
import com.morestudio.craftify.ui.detail.DetailActivity

class NoteAdapter(var notes: List<Note?>) : RecyclerView.Adapter<NoteAdapter.VH>() {

    // Öğelerin tarihlerine göre sıralanmış bir notlar listesi
    private val sortedNotes = notes.sortedByDescending { it?.createdAt }
    private var id = 0


    //ViewHolder
    inner class VH(val view : NoteItemBinding) : RecyclerView.ViewHolder(view.root), View.OnClickListener{
        fun bind(note : Note){
            view.title.text = note.title
            view.content.text = note.content
            view.createdAt.text = note.createdAt
            id = note.noteId
        }
        init {
            itemView.setOnClickListener(this)
        }

        //onClick
        override fun onClick(v: View?) {
            val position = adapterPosition
            val intent = Intent(view.root.context, DetailActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("title", view.title.text )
            intent.putExtra("id", id)
            intent.putExtra("content", view.content.text )
            intent.putExtra("createdAt", view.createdAt.text )
            intent.putExtra("isPinned", view.isPinned.visibility)
            view.root.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return VH(NoteItemBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return sortedNotes.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val note = sortedNotes.get(position)
        holder.view.content.text = note?.content
        holder.view.title.text = note?.title
        holder.view.createdAt.text = note?.createdAt

        if(note?.isPinned == true){
            holder.view.isPinned.visibility = View.VISIBLE
        }
    }

}