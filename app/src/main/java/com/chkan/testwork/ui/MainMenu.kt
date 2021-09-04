package com.chkan.testwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.chkan.testwork.MainActivity
import com.chkan.testwork.databinding.FragmentMainMenuBinding
import com.chkan.testwork.utils.MenuAdapter
import com.chkan.testwork.utils.MenuListListener



class MainMenu: Fragment() {

    private val viewModel: MenuViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainMenuBinding.inflate(inflater)

        // Позволяет привязке данных наблюдать за LiveData в течение жизненного цикла этого фрагмента
        binding.lifecycleOwner = this

        // Предоставление привязки доступа к WarehouseViewModel (xml олжна быть эта переменная)
        binding.viewModel = viewModel

        //назначаем ресайклеру адаптер и слушатель кликов с обработкой в viewModel
        val adapter = MenuAdapter(MenuListListener { Id ->
            viewModel.onMenuClicked(Id)
        } )
        binding.rvMenu.adapter = adapter

        //получаем данные для отрисовки ресайклера
        viewModel.titles.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.navigateToMenu.observe(viewLifecycleOwner,{id->
            id?.let {
                this.findNavController().navigate(MainMenuDirections.
                actionMainMenuToMenuFrag(id)
                    )
                viewModel.onMenuNavigated()
            }

        })

        return binding.root
    }

}