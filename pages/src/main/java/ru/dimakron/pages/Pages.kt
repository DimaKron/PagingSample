package ru.dimakron.pages

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class Pages private constructor(private val recyclerView: RecyclerView,
                                private val callbacks: Callbacks,
                                private val loadingTriggerThreshold: Int){

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

        val visibleItemCount = recyclerView.childCount

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

        val totalItemCount = layoutManager.itemCount

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + loadingTriggerThreshold) || totalItemCount == 0) {
            if (!callbacks.isLoading() && !callbacks.hasLoadedAllItems()) {
                callbacks.onLoadMore()
            }
        }
    }

    class Builder(private val recyclerView: RecyclerView,
                  private val callbacks: Callbacks){

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

            return Pages(recyclerView, callbacks, loadingTriggerThreshold)
        }
    }

    interface Callbacks {

        fun onLoadMore()

        fun isLoading(): Boolean

        fun hasLoadedAllItems(): Boolean

    }
}