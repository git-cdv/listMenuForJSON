package com.chkan.testwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chkan.testwork.MainActivity
import com.chkan.testwork.databinding.FragmentMenuBinding
import com.chkan.testwork.utils.MenuAdapter
import com.chkan.testwork.utils.MenuListListener

class Menu: Fragment() {

    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val args = MenuArgs.fromBundle(requireArguments()).toBundle().getInt("clickedId")
        viewModel.getDataForCat(args)

        val binding = FragmentMenuBinding.inflate(inflater)

        // Позволяет привязке данных наблюдать за LiveData в течение жизненного цикла этого фрагмента
        binding.lifecycleOwner = this

        // Предоставление привязки доступа к WarehouseViewModel (xml олжна быть эта переменная)
        binding.viewModel = viewModel

        //назначаем ресайклеру адаптер и слушатель кликов с обработкой в viewModel
        val adapter = MenuAdapter(MenuListListener { Id ->
            viewModel.onMenuClicked(Id)
        } )
        binding.rvMenuMid.adapter = adapter

        //получаем данные для отрисовки ресайклера
        viewModel.cat.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.navigateToMenu.observe(viewLifecycleOwner,{id->
            id?.let {
                this.findNavController().navigate(MenuDirections.
                actionMenuFragToItemsFrag(id)
                )
                viewModel.onMenuNavigated()
            }

        })

        return binding.root
    }

}
