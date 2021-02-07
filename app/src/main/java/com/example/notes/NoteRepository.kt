package com.example.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao:NotesDAO) {
    val allNotes: LiveData<List<Notes>> = noteDao.getAllNotes()
    suspend fun insert(note:Notes) {
        noteDao.insert(note)
    }
    suspend fun delete(note:Notes){
        noteDao.delete(note)
    }

    suspend fun  update(note:Notes){
        noteDao.update(note)
    }
}