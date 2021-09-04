package com.chkan.testwork.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.chkan.testwork.MainActivity
import com.chkan.testwork.databinding.FragmentItemsBinding
import com.chkan.testwork.databinding.FragmentMainMenuBinding
import com.chkan.testwork.utils.Constans
import com.chkan.testwork.utils.MenuAdapter
import com.chkan.testwork.utils.MenuListListener

class Items: Fragment() {

    private val viewModel: MenuViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val args = ItemsArgs.fromBundle(requireArguments()).toBundle().getInt("clickedItemId")
        viewModel.getDataForSubcat(args)

        Log.d(Constans.TAG, "args -> $args")

        val binding = FragmentItemsBinding.inflate(inflater)
        //назначаем ресайклеру адаптер и слушатель кликов с обработкой в viewModel
        val adapter = MenuAdapter(MenuListListener { Id ->
            Log.d(Constans.TAG, "Item clicked")
        } )
        binding.rvItems.adapter = adapter

        //получаем данные для отрисовки ресайклера
        viewModel.items.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })
        // Позволяет привязке данных наблюдать за LiveData в течение жизненного цикла этого фрагмента
        binding.lifecycleOwner = this

        // Предоставление привязки доступа к WarehouseViewModel (xml олжна быть эта переменная)
        binding.viewModel = viewModel

        return binding.root
    }

}