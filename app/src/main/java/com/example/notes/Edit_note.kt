package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_note.*
import kotlinx.android.synthetic.main.activity_write_note.*

class Edit_note : AppCompatActivity() {
    var flag=0
    companion object{
        const val NOTE_EDIT = "note_edit"
        const val NOTE_ID = "note_id"
        const val NOTE_HEADING = "note_heading"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        val noteEdit = intent.getStringExtra(NOTE_EDIT)
        editinput.text= Editable.Factory.getInstance().newEditable(noteEdit)
        val noteheading = intent.getStringExtra(NOTE_HEADING)
        edittitle.text=Editable.Factory.getInstance().newEditable(noteheading)
    }

    fun editsubmit(view: View) {
        flag=1
        val edittitle =  edittitle.text.toString()
        val editinput= editinput.text.toString()
        val noteid = intent.getIntExtra(NOTE_ID,-1)
        if(edittitle.isEmpty())
        {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(MainActivity.NOTE_TITLE,editinput)
            intent.putExtra(MainActivity.NOTET_EXTRA,editinput)
            intent.putExtra(MainActivity.FLAG,flag)
            intent.putExtra(MainActivity.ID,noteid)
            startActivity(intent)
        }
       else if(editinput.isNotEmpty())
        {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(MainActivity.NOTE_TITLE,edittitle)
            intent.putExtra(MainActivity.NOTET_EXTRA,editinput)
            intent.putExtra(MainActivity.FLAG,flag)
            intent.putExtra(MainActivity.ID,noteid)
            startActivity(intent)
        }
    }
}