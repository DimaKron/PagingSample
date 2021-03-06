package ru.dimakron.paging.ui.pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import ru.dimakron.pages.Pages
import ru.dimakron.paging.databinding.ActivityPagesBinding
import ru.dimakron.paging.model.LoadingState

class PagesActivity: MvpAppCompatActivity(), IPagesActivity {

    companion object {
        fun makeIntent(context: Context) =
            Intent(context, PagesActivity::class.java)
    }


    private val presenter by moxyPresenter { get<PagesPresenter>() }

    private lateinit var binding: ActivityPagesBinding

    private var adapter: DigitsAdapter? = null
    private var pages: Pages? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DigitsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        pages = Pages.Builder(binding.recyclerView, presenter::processLoadMore)
            .setLoadingTriggerThreshold(1)
            .build()
    }

    override fun onDestroy() {
        pages?.unbind()
        pages = null
        adapter = null
        super.onDestroy()
    }

    override fun showLoadingState(loadingState: LoadingState) {
        pages?.isLoading = loadingState == LoadingState.LOADING
    }

    override fun showDigits(items: List<Int>, hasMore: Boolean) {
        adapter?.items = items
        pages?.hasMore = hasMore
    }
}