package com.chkan.testwork.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chkan.testwork.MainActivity
import com.chkan.testwork.R


class MainMenu: Fragment() {

    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context as MainActivity
        val centerlist = resources.getStringArray(R.array.region1)

        val lv = view.findViewById(R.id.lv_main) as ListView
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, centerlist)
        lv.adapter = adapter
        viewModel.getMenu()
    }

}