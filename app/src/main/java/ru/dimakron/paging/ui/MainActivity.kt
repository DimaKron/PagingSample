package ru.dimakron.paging.ui

import android.os.Bundle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import ru.dimakron.paging.databinding.ActivityMainBinding

class MainActivity: MvpAppCompatActivity(), IMainActivity {

    private val presenter by moxyPresenter { get<MainPresenter>() }

    private lateinit var binding: ActivityMainBinding

    private var adapter: DigitsAdapter? = null

    private var pagingDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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