package ru.dimakron.paging.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class DigitsAdapter: PagingDataAdapter<Int, DigitHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DigitHolder.create(parent)

    override fun onBindViewHolder(holder: DigitHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Int>() {

            override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

        }
    }
}