package com.chkan.testwork.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chkan.testwork.network.MenuApi
import com.chkan.testwork.utils.Constans
import kotlinx.coroutines.*

class MainMenuViewModel : ViewModel() {

    init {
        getMenu()
    }

    fun getMenu() {
        viewModelScope.launch {
            try {
                val listResult = MenuApi.retrofitService.getMenu()
                Log.d(Constans.TAG, "listResult -> $listResult")
            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")
            }
        }
    }


    }
