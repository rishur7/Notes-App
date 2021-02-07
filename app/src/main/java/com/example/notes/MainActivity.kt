package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NoteRVAdapter.NoteItemClicked {
    companion object{
        const val NOTE_TITLE = "note_title"
        const val NOTET_EXTRA = "noteT_extra"
        const val FLAG = "flag"
        const val ID = "id"
    }
    lateinit var viewModel: NoteViewModel // jab initiate hoga tab declare hojaega lateinit se
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NoteRVAdapter(this,this)
        recyclerView.adapter=adapter

 viewModel = ViewModelProvider(this,
 ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer{ list->
          list?.let{
                adapter.updateNotes(it) // ?. checks nullability
            }
        })
        val noteTitle= intent.getStringExtra(NOTE_TITLE)
        val noteT = intent.getStringExtra(NOTET_EXTRA)
        val flag = intent.getIntExtra(FLAG,0)

        //UPDATE NOTE
        if(flag==1 && noteT != null && noteTitle != null)
        {
            update(Notes(noteTitle,noteT))
            Toast.makeText(this,"Note Updated", Toast.LENGTH_SHORT).show()
        }
       //ADD NOTE
       else if (flag==0 && noteT != null && noteTitle != null) {
            viewModel.add(Notes(noteTitle,noteT))
            Toast.makeText(this,"Note Created", Toast.LENGTH_SHORT).show()
        }
    }

    override fun OnClickListenerDel(note: Notes) {
        //DELETE NOTE
        viewModel.delete(note)
        Toast.makeText(this,"Note Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun OnClickListenerText(note: Notes) {
        val intent = Intent(this,Edit_note::class.java)
        intent.putExtra(Edit_note.NOTE_EDIT,note.text)
        intent.putExtra(Edit_note.NOTE_HEADING,note.title)
        intent.putExtra(Edit_note.NOTE_ID,note.id)
        startActivity(intent)
    }

    fun update(note:Notes) {
        val newid = intent.getIntExtra(ID,-1)
        viewModel.add(note)
        note.id=newid // reinitializing it to old id for update
        viewModel.update(note)
    }

    fun create(view: View) {
 val intent = Intent(this,write_note::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_to_up,R.anim.slide_to_exit_down)
    }

    //-------Main Menu-------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.newmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about -> {
                val intent= Intent(this,AboutMe::class.java)
                startActivity(intent)
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
}