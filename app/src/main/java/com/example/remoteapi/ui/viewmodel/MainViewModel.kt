package com.example.remoteapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.remoteapi.data.entity.BeerEntity
import com.example.remoteapi.domain.mapper.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>
): ViewModel(){
    val beerPagingFlow = pager
        .flow
        .map { pagingData->
            pagingData.map { it.toBeer() }
        }
        .cachedIn(viewModelScope)
}
