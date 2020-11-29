package com.example.roomexample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NameViewModel(private val repo: NameRepository) : ViewModel() {

    val allNames: LiveData<List<Name>> = repo.allNames.asLiveData()

    fun insert(name: Name) = viewModelScope.launch {
        repo.insert(name)
    }
}

class NameViewModelFactory(private val repository: NameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}