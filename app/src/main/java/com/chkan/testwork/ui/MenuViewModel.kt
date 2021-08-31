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

    val results = MutableLiveData<MenuModel>()
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun getMenu() {
        viewModelScope.launch {

            try {
                val response = MenuApi.retrofitService.getMenu()
                if (response.isSuccessful) {
                    val data = response.body()
                    results.value = data!!
                    Log.d(Constans.TAG, "listResult -> ${results.value}")
                    _status.value="Загружено успешно"
                }
            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")
                _status.value="Ошибка загрузки: ${e.message}"
            }

            /*val data = MenuApi.retrofitService.getMenu()
            results.value = data
            Log.d(Constans.TAG, "listResult -> ${results.value}")*/
        }
  /*      viewModelScope.launch {

            try {
                listResult = MenuApi.retrofitService.getMenu()
                Log.d(Constans.TAG, "listResult -> $listResult")
                _status.value="Загружено успешно"

            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")
                _status.value="Ошибка загрузки: ${e.message}"
            }
        }*/
    }
fun getTitleMenu(): Array<String>{
    val model = results.value
    return arrayOf(model!!.menus[0].title,model.menus[1].title,model.menus[2].title)
}

    }
