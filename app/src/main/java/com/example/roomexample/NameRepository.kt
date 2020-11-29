package com.example.roomexample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class NameRepository(private val nameDao: NameDao) {
    val allNames: Flow<List<Name>> = nameDao.getNames()

    @WorkerThread
    suspend fun insert(name: Name){
        nameDao.insert(name)
    }
}