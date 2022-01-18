package ru.dimakron.pages

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class Pages private constructor(private val recyclerView: RecyclerView,
                                private val onLoadMore: () -> Unit,
                                private val loadingTriggerThreshold: Int){

    var isLoading = false
    var hasMore = true

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            checkEndOffset()
        }
    }

    init {
        recyclerView.addOnScrollListener(onScrollListener)
        checkEndOffset()
    }

    fun unbind() {
        recyclerView.removeOnScrollListener(onScrollListener)
    }

    private fun checkEndOffset() {
        val layoutManager = recyclerView.layoutManager

        val firstVisibleItemPosition = when(layoutManager){
            is LinearLayoutManager -> {
                layoutManager.findFirstVisibleItemPosition()
            }
            is StaggeredGridLayoutManager -> {
                // https://code.google.com/p/android/issues/detail?id=181461
                if (layoutManager.getChildCount() > 0) layoutManager.findFirstVisibleItemPositions(null)[0] else 0
            }
            else -> {
                throw IllegalStateException("LayoutManager needs to subclass LinearLayoutManager or StaggeredGridLayoutManager")
            }
        }

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager.itemCount

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + loadingTriggerThreshold) || totalItemCount == 0) {
            if (!isLoading && hasMore) {
                onLoadMore()
            }
        }
    }

    class Builder(private val recyclerView: RecyclerView,
                  private val onLoadMore: () -> Unit){

        private var loadingTriggerThreshold = 5

        fun setLoadingTriggerThreshold(threshold: Int): Builder {
            this.loadingTriggerThreshold = threshold
            return this
        }

        fun build(): Pages {
            if (recyclerView.adapter == null) {
                throw IllegalStateException("Adapter needs to be set!")
            }

            if (recyclerView.layoutManager == null) {
                throw IllegalStateException("LayoutManager needs to be set on the RecyclerView")
            }

            return Pages(recyclerView, onLoadMore, loadingTriggerThreshold)
        }
    }
}