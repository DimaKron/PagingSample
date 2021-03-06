package ru.dimakron.paging.ui.paginate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dimakron.paging.ui.common.DigitHolder

class DigitsAdapter: RecyclerView.Adapter<DigitHolder>() {

    var items = listOf<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DigitHolder.create(parent)

    override fun onBindViewHolder(holder: DigitHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}