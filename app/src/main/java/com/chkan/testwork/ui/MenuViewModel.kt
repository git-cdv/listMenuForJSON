package com.chkan.testwork.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chkan.testwork.model.MenuModel
import com.chkan.testwork.network.MenuApi
import com.chkan.testwork.utils.Constans
import kotlinx.coroutines.*

class MenuViewModel : ViewModel() {

    lateinit var listResult : MenuModel
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getMenu() {
        viewModelScope.launch {

            try {
                listResult = MenuApi.retrofitService.getMenu()
                Log.d(Constans.TAG, "listResult -> $listResult")
                _status.value="Загружено успешно"

            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")
                _status.value="Ошибка загрузки: ${e.message}"
            }
        }
    }


    }
