package com.example.orufyassign.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orufyassign.R
import com.example.orufyassign.databinding.ActivityMainBinding
import com.example.orufyassign.di.adapter.AnimalAdapter
import com.example.orufyassign.di.api.RetrofitInstance
import com.example.orufyassign.util.Constants.Companion.API_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AnimalAdapter
    private var currentPage = 1
    private val pageSize = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchData()
    }

    private fun setupRecyclerView() {
        adapter = AnimalAdapter(mutableListOf())
        binding.rvAnimal.adapter = adapter
        binding.rvAnimal.layoutManager = LinearLayoutManager(this)

        binding.rvAnimal.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    fetchData()
                }
            }
        })
    }

    private var isLoading = false

    private  fun fetchData() {
        if (isLoading) return

        isLoading = true
        binding.loadingBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            delay(2000)
            val response = RetrofitInstance.api.getAnimals(
                apiKey = "45122003-e2ab61e47ff72bb489312b2f8",
                query = "animals",
                imageType = "photo",
                page = currentPage,
                perPage = pageSize
            )
            adapter.addData(response.hits)
            binding.loadingBar.visibility = View.GONE
            currentPage++
            isLoading = false
        }
    }
}
