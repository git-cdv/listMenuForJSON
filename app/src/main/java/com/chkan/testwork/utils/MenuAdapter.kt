package com.chkan.testwork.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chkan.testwork.databinding.RvItemBinding
import com.chkan.testwork.model.Titles

class MenuAdapter(val clickListener:MenuListListener): ListAdapter<Titles,
        MenuAdapter.MenuViewHolder>(DiffCallback) {

        class MenuViewHolder(
            private var binding: RvItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(mTitle: Titles, clickListener: MenuListListener) {
                //где product название <data><variable> из list_item_wh.xml
                //т.е. какие вью мы с <variable> во list_item_wh.xml связали - такие и покажутся
                binding.titles = mTitle
                binding.clickListener = clickListener
                // Это важно, потому что это приводит к немедленному выполнению привязки данных,
                // что позволяет RecyclerView производить правильные измерения размера представления
                binding.executePendingBindings()
            }
        }

        /**
         * Создаем новые представления элементов [RecyclerView] (вызываемые диспетчером макета)
         */
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MenuAdapter.MenuViewHolder {
            return MenuViewHolder(
                RvItemBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
            val mTitle = getItem(position)
            holder.bind(mTitle,clickListener)
        }

    companion object DiffCallback : DiffUtil.ItemCallback<Titles>() {

        override fun areItemsTheSame(oldItem: Titles, newItem: Titles): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Titles, newItem: Titles): Boolean {
            return oldItem.title == newItem.title
        }
    }

    }

    class MenuListListener(val clickListener: (id: Int) -> Unit) {
        fun onClick(title: Titles) = clickListener(title.id)
    }