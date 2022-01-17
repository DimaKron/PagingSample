package ru.dimakron.paging.ui.paginate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.paginate.Paginate
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import ru.dimakron.paging.databinding.ActivityPaginateBinding
import ru.dimakron.paging.model.LoadingState

class PaginateActivity: MvpAppCompatActivity(), IPaginateActivity {

    companion object {
        fun makeIntent(context: Context) =
            Intent(context, PaginateActivity::class.java)
    }


    private val presenter by moxyPresenter { get<PaginatePresenter>() }

    private lateinit var binding: ActivityPaginateBinding

    private var adapter: DigitsAdapter? = null

    private var isItemsLoading = false
    private var hasMoreItems = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaginateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DigitsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        Paginate.with(binding.recyclerView, paginateCallbacks)
            .setLoadingTriggerThreshold(1)
            .addLoadingListItem(true)
            .build()
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }

    override fun showLoadingState(loadingState: LoadingState) {
        isItemsLoading = loadingState == LoadingState.LOADING
    }

    override fun showDigits(items: List<Int>, hasMore: Boolean) {
        adapter?.items = items
        hasMoreItems = hasMore
    }

    private val paginateCallbacks = object: Paginate.Callbacks {

        override fun onLoadMore() {
            presenter.processLoadMore()
        }

        override fun isLoading() = isItemsLoading

        override fun hasLoadedAllItems() = !hasMoreItems

    }
}