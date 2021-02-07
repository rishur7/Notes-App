package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context: Context, private val listener: NoteItemClicked): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {
    private val allNotes = ArrayList<Notes>() // Link between Data and UI

inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val textView:TextView= itemView.findViewById(R.id.text)
    val deleteButton:ImageView = itemView.findViewById(R.id.img)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.deleteButton.setOnClickListener{
            listener.OnClickListenerDel(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.textView.setOnClickListener{
            listener.OnClickListenerText(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote= allNotes[position]
        holder.textView.text= currentNote.title
    }
    fun updateNotes(newNote: List<Notes>)
    {
        allNotes.clear()
        allNotes.addAll(newNote)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
       return allNotes.size
    }
    interface NoteItemClicked{ //is type of listener/service not a class
        fun OnClickListenerDel(note:Notes)
        fun OnClickListenerText(note:Notes)
    }
}