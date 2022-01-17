package ru.dimakron.paging.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dimakron.paging.databinding.ActivityMainBinding
import ru.dimakron.paging.ui.paginate.PaginateActivity
import ru.dimakron.paging.ui.paging3.Paging3Activity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.paging3Button.setOnClickListener { startActivity(Paging3Activity.makeIntent(this)) }
        binding.paginateButton.setOnClickListener { startActivity(PaginateActivity.makeIntent(this)) }
    }
}