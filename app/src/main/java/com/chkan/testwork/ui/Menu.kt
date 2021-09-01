package com.chkan.testwork.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chkan.testwork.MainActivity
import com.chkan.testwork.databinding.FragmentMenuBinding
import com.chkan.testwork.utils.Constans
import com.chkan.testwork.utils.MenuAdapter
import com.chkan.testwork.utils.MenuListListener

class Menu: Fragment() {

    private val viewModel: MenuMidViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val argValue = requireArguments().getInt("arg")
        Log.d(Constans.TAG, "argValue -> $argValue")
        viewModel.getMenu(argValue)

        val binding = FragmentMenuBinding.inflate(inflater)
        //назначаем ресайклеру адаптер и слушатель кликов с обработкой в viewModel
        binding.rvMenuMid.adapter = MenuAdapter(MenuListListener { Id ->
            val act : MainActivity = activity as MainActivity
            act.onItemsClicked(Id)
        } )
        // Позволяет привязке данных наблюдать за LiveData в течение жизненного цикла этого фрагмента
        binding.lifecycleOwner = this

        // Предоставление привязки доступа к WarehouseViewModel (xml олжна быть эта переменная)
        binding.viewModel = viewModel

        return binding.root
    }

}
