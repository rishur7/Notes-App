package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Notes>>
    private val repository: NoteRepository
    init { //init pehle chlta hai jab class shuru hoti hai
        val dao= NoteDatabase.getDatabase(application).getNotesDao()
        repository= NoteRepository(dao)
        allNotes=repository.allNotes
    }
    fun delete(note:Notes) = viewModelScope.launch (Dispatchers.IO){
        repository.delete(note) //viewModel ke scope ke andr launch krrhe hai ye sara process bg me and Dispatchers.IO means input output operation hai ye
    }
    fun add(note:Notes) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }
    fun update(note:Notes) = viewModelScope.launch (Dispatchers.IO){
        repository.update(note)
    }
}