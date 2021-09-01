package com.chkan.testwork.ui

import android.text.TextUtils.indexOf
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
import kotlinx.coroutines.launch

class ItemsViewModel : ViewModel() {

    private var _items = MutableLiveData<MutableList<Titles>>()
    val items: LiveData<MutableList<Titles>> = _items

    private val _results = MutableLiveData<MenuModel>()
    val results: LiveData<MenuModel> = _results

    fun getMenu(argCat:Int, argItem:Int) {
        viewModelScope.launch {
            Log.d(Constans.TAG, "argCat -> $argCat,argItem -> $argItem")
            try {
                val response = MenuApi.retrofitService.getMenu()
                if (response.isSuccessful) {
                    _results.value = response.body()
                    var index:Int=0
                    val list: List<Categories> = results.value!!.menus[argCat-1].categories

                    for(x in list.indices){
                        if(list[x].id.toInt()==argItem)
                        {index=x}
                    }
                    Log.d(Constans.TAG, "index -> $index")

                    val entrees: MutableList<Titles> = mutableListOf()
                    for(x in results.value!!.menus[argCat-1].categories[index].subcategories.indices){
                        entrees.add(
                            Titles(
                            0,results.value!!.menus[argCat-1].categories[index].subcategories[x].title)
                        )
                    }
                    _items.value=entrees
                    Log.d(Constans.TAG, "size -> ${results.value!!.menus[argCat-1].categories[index].subcategories.indices}")
                    Log.d(Constans.TAG, "listResult -> ${_items.value}")

                }
            } catch (e: Exception) {
                Log.d(Constans.TAG, "Exception -> ${e.message}")

            }
        }

    }

}