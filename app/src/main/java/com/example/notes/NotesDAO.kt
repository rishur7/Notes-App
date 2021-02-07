package com.example.notes
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Notes)
//suspend runs in bg to avoid lagging

    @Update
    suspend fun update(note:Notes)

    @Delete
    suspend fun delete(note: Notes)

// end me sare notes ikathe krlo
    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}