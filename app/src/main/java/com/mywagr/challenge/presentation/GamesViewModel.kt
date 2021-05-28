package com.mywagr.challenge.presentation

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.mywagr.challenge.repository.GamesDataRepository
import com.mywagr.challenge.repository.GamesResult
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * View Model that emits updates to the respective ui observing the live data
 */
class GamesViewModel(application :Application) : AndroidViewModel(application) {
    private val repository by lazy { GamesDataRepository.get() }
    private val _searchResult = MutableLiveData<GamesResult>()

    val gamesResult: LiveData<GamesResult>
    get()=_searchResult

     fun getGames() =viewModelScope.launch {
            var result: GamesResult? = null

            withContext(Dispatchers.Default) {
                result = repository.getGames()
                _searchResult.postValue(result)
            }
        }
}

