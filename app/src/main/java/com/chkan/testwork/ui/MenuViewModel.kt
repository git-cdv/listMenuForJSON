package com.chkan.testwork.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chkan.testwork.model.Categories
import com.chkan.testwork.model.MenuModel
import com.chkan.testwork.model.Titles
import com.chkan.testwork.network.MenuApi
import com.chkan.testwork.utils.Constans
import kotlinx.coroutines.*

class MenuViewModel : ViewModel() {

    lateinit var results: MenuModel
    var argCat: Int = 0

    private var _titles = MutableLiveData<List<Titles>>()
    val titles: LiveData<List<Titles>> = _titles

    private var _cat = MutableLiveData<List<Titles>>()
    val cat: LiveData<List<Titles>> = _cat

    private var _items = MutableLiveData<List<Titles>>()
    val items: LiveData<List<Titles>> = _items

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
                    results = response.body()!!
                    getDataForMenu()
                }
            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")
            }
        }

    }

    fun getDataForSubcat(argItem:Int) {
        var index:Int=0
        val list: List<Categories> = results.menus[argCat-1].categories

        for(x in list.indices){
            if(list[x].id.toInt()==argItem)
            {index=x}
        }

        val entrees: MutableList<Titles> = mutableListOf()
        for(x in results.menus[argCat-1].categories[index].subcategories.indices){
            entrees.add(
                Titles(
                    0,results.menus[argCat-1].categories[index].subcategories[x].title)
            )
        }
        _items.value=entrees
    }

    fun getDataForCat(arg:Int) {
        val entrees: MutableList<Titles> = mutableListOf()
        for(x in results.menus[arg-1].categories.indices){
            entrees.add(Titles(
                results.menus[arg-1].categories[x].id.toInt(),results.menus[arg-1].categories[x].title))
        }
        _cat.value=entrees
        argCat = arg
    }

    private fun getDataForMenu() {
        val list: MutableList<Titles> = mutableListOf()
        for (x in results.menus.indices){
            list.add(Titles(
                results.menus[x].id.toInt(),
                results.menus[x].title))
        }
        _titles.value = list
    }

}
