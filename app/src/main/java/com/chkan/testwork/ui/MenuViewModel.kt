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

    private var _menus = MutableLiveData<List<Titles>>()
    val menus: LiveData<List<Titles>> = _menus

    private var _titles = MutableLiveData<List<Titles>>()
    val titles: LiveData<List<Titles>> = _titles

    private val _results = MutableLiveData<MenuModel>()
    val results: LiveData<MenuModel> = _results


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