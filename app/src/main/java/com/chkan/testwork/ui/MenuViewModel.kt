package com.chkan.testwork.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chkan.testwork.model.MenuModel
import com.chkan.testwork.model.Titles
import com.chkan.testwork.network.MenuApi
import com.chkan.testwork.utils.Constans
import kotlinx.coroutines.*

class MenuViewModel : ViewModel() {

    private var _titles = MutableLiveData<List<Titles>>()
    val titles: LiveData<List<Titles>> = _titles

    private val _results = MutableLiveData<MenuModel>()
    val results: LiveData<MenuModel> = _results

    //объект для управления навигацией
    private val _navigateToMenu = MutableLiveData<Int?>()
    val navigateToMenu
        get() = _navigateToMenu

    //для начала навигации
    fun onMenuClicked(id: Int){
        _navigateToMenu.value = id
    }
    //для конца навигации
    fun onMenuNavigated() {
        _navigateToMenu.value = null
    }

    init {
        getMenu()
    }

    fun getMenu() {
        viewModelScope.launch {

            try {
                val response = MenuApi.retrofitService.getMenu()
                if (response.isSuccessful) {
                    _results.value = response.body()
                    _titles.value = listOf(Titles(
                        results.value!!.menus[0].id.toInt(),
                        results.value!!.menus[0].title),Titles(
                        results.value!!.menus[1].id.toInt(),
                        results.value!!.menus[1].title),Titles(
                        results.value!!.menus[2].id.toInt(),
                        results.value!!.menus[2].title))
                    Log.d(Constans.TAG, "listResult -> ${results.value}")

                }
            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")

            }
        }

    }

}
