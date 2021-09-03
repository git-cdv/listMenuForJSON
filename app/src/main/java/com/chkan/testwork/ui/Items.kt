package com.chkan.testwork.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chkan.testwork.MainActivity
import com.chkan.testwork.databinding.FragmentItemsBinding
import com.chkan.testwork.databinding.FragmentMainMenuBinding
import com.chkan.testwork.utils.Constans
import com.chkan.testwork.utils.MenuAdapter
import com.chkan.testwork.utils.MenuListListener

class Items: Fragment() {

    private val viewModel: ItemsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val argCat = requireArguments().getInt("argCat")
        val argItem = requireArguments().getInt("argItem")

        Log.d(Constans.TAG, "argCat -> $argCat,argItem -> $argItem")
        viewModel.getMenu(argCat,argItem)


        val binding = FragmentItemsBinding.inflate(inflater)
       /* //назначаем ресайклеру адаптер и слушатель кликов с обработкой в viewModel
        binding.rvItems.adapter = MenuAdapter(MenuListListener { Id ->
            Log.d(Constans.TAG, "MenuListListener -> $Id")
        } )*/
        // Позволяет привязке данных наблюдать за LiveData в течение жизненного цикла этого фрагмента
        binding.lifecycleOwner = this

        // Предоставление привязки доступа к WarehouseViewModel (xml олжна быть эта переменная)
        binding.viewModel = viewModel

        return binding.root
    }

}