package com.chkan.testwork.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chkan.testwork.databinding.RvItemBinding
import com.chkan.testwork.model.Titles

class MenuAdapter (val clickListener:MenuListListener): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var data = listOf<Titles>()
        set(value) {
            field = value
            //обновляет ВЕСЬ RecyclerView при изменении списка
            notifyDataSetChanged()
        }
    // Адаптеру нужно знать, сколько элементов нужно предоставить компоненту
    override fun getItemCount() = data.size

    //связываем используемые поля с данными
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,clickListener)
    }
    //нужно указать идентификатор макета для отдельного элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RvItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Titles, clickListener: MenuListListener) {
            //где titles название <data><variable> из rv_item.xml
            //т.е. какие вью мы с <variable> во rv_item.xml связали (вот так "@{titles.title}") - такие и покажутся
            binding.titles = item
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val bind = RvItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(bind)
            }
        }
    }
}

class MenuListListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(title: Titles) = clickListener(title.id)
}

