package ru.dimakron.paging.ui.paging3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import ru.dimakron.paging.databinding.ActivityPaging3Binding


class Paging3Activity: MvpAppCompatActivity(), IPaging3Activity {

    companion object{
        fun makeIntent(context: Context) =
            Intent(context, Paging3Activity::class.java)
    }

    private val presenter by moxyPresenter { get<Paging3Presenter>() }

    private lateinit var binding: ActivityPaging3Binding

    private var adapter: DigitsAdapter? = null

    private var pagingDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaging3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DigitsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        pagingDisposable?.dispose()
        pagingDisposable = null
        adapter = null
        super.onDestroy()
    }

    override fun initDigitsPaging(flowable: Flowable<PagingData<Int>>) {
        if (pagingDisposable == null) {
            pagingDisposable = flowable.subscribe { adapter?.submitData(lifecycle, it) }
        }
    }
}