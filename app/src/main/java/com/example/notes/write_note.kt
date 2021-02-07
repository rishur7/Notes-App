package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write_note.*

class write_note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)
    }

    fun submit(view: View) {
        val noteText= input.text.toString()
        val noteTitle= heading.text.toString()
        if(noteTitle.isEmpty() && noteText.isNotEmpty())
        {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(MainActivity.NOTE_TITLE,noteText)
            intent.putExtra(MainActivity.NOTET_EXTRA,noteText)
            startActivity(intent)
        }
        else if(noteTitle.isNotEmpty() && noteText.isNotEmpty())
        {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(MainActivity.NOTE_TITLE,noteTitle)
            intent.putExtra(MainActivity.NOTET_EXTRA,noteText)
            startActivity(intent)
        }
        else if(noteTitle.isEmpty() && noteText.isEmpty())
        {
            Toast.makeText(this,"Empty Note",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_to_down,R.anim.slide_to_exit_up)
    }
}