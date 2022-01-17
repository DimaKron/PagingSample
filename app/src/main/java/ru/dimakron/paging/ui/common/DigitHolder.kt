package ru.dimakron.paging.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dimakron.paging.databinding.ItemDigitBinding

class DigitHolder private constructor(private val binding: ItemDigitBinding): RecyclerView.ViewHolder(binding.root) {

    companion object{
        fun create(parent: ViewGroup): DigitHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDigitBinding.inflate(layoutInflater, parent, false)
            return DigitHolder(binding)
        }
    }

    fun bind(item: Int){
        binding.textView.text = item.toString()
    }
}