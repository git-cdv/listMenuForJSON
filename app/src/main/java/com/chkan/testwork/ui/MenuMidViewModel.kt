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

class MenuMidViewModel : ViewModel() {

    private var _menus = MutableLiveData<MutableList<Titles>>()
    val menus: LiveData<MutableList<Titles>> = _menus

    private val _results = MutableLiveData<MenuModel>()
    val results: LiveData<MenuModel> = _results

    fun getMenu(arg:Int) {
        viewModelScope.launch {
            Log.d(Constans.TAG, "arg -> $arg")
            try {
                val response = MenuApi.retrofitService.getMenu()
                if (response.isSuccessful) {
                    _results.value = response.body()
                    val entrees: MutableList<Titles> = mutableListOf()
                    for(x in results.value!!.menus[arg-1].categories.indices){
                        entrees.add(Titles(
                            results.value!!.menus[arg-1].categories[x].id.toInt(),results.value!!.menus[arg-1].categories[x].title))
                    }
                    _menus.value=entrees
                    Log.d(Constans.TAG, "size -> ${results.value!!.menus[arg-1].categories.indices}")
                    Log.d(Constans.TAG, "listResult -> ${_menus.value}")

                }
            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")

            }
        }

    }

}
