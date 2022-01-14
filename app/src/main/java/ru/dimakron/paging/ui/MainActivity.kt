package ru.dimakron.paging.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import ru.dimakron.paging.databinding.ActivityMainBinding

class MainActivity: MvpAppCompatActivity(), IMainActivity {

    private val presenter by moxyPresenter { get<MainPresenter>() }

    private lateinit var binding: ActivityMainBinding

    private var adapter: DigitsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DigitsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }

    override fun showDigits(items: List<Int>) {
        adapter?.items = items
    }
}