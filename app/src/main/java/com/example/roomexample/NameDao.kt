package com.example.roomexample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NameDao {

    @Query("SELECT * FROM name_table")
    fun getNames(): Flow<List<Name>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Name)

    @Query("DELETE FROM name_table")
    suspend fun deleteAll()

}