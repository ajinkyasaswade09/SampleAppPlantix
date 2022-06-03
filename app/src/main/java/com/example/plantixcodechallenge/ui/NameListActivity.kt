package com.example.plantixcodechallenge.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantixcodechallenge.R
import com.example.plantixcodechallenge.databinding.ActivityNameListBinding
import com.example.plantixcodechallenge.ui.adapter.NameListAdapter
import com.example.plantixcodechallenge.viewmodel.NameListViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NameListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityNameListBinding
    private val adapter = NameListAdapter()

    private val viewModel: NameListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NameListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_name_list)
        setupRecyclerView()
        initObservers()
        viewModel.getNameList()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.apply {
            recyclerView.addItemDecoration(
                DividerItemDecoration(baseContext, layoutManager.orientation)
            )
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }
    }

    private fun initObservers() {
        viewModel.nameList.observe(this) { list ->
            binding.apply {
                adapter.setList(list)
                progressBar.visibility = View.GONE
            }
        }

        viewModel.loading.observe(this) {
            binding.apply {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}
