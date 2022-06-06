package com.wednesday.test.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wednesday.test.common.Response
import com.wednesday.test.databinding.ActivityCatsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.snackbar.longSnack

class CatActivity : AppCompatActivity() {

    //Inject our ViewModel using Koin
    private val viewModel: CatViewModel by viewModel()
    private lateinit var binding: ActivityCatsBinding
    private lateinit var adapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Cute Cats on a Wednesday"

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        //Update our RecyclerView with a List of Cute Cats
        viewModel.response.observe(this) { response ->
            when(response) {
                is Response.Loading -> {
                    binding.loadingIndicator.visibility = View.VISIBLE
                    binding.catsList.visibility = View.GONE
                }
                is Response.Success -> {
                    //Hide our Loading dialog & Show our list again
                    binding.loadingIndicator.visibility = View.GONE
                    binding.catsList.visibility = View.VISIBLE
                    //Submit our list of cute cats to the RecyclerView
                    adapter.differ.submitList(response.data)
                }
                is Response.Error -> {
                    //Hide both our Loading & List
                    binding.loadingIndicator.visibility = View.GONE
                    binding.catsList.visibility = View.GONE
                    //Show our error message in a Snackbar
                    binding.root.longSnack(response.message.toString())
                }
            }
        }
    }

    private fun setupRecyclerView() {
        //Set the LayoutManager to StaggeredLayoutManager
        binding.catsList.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = CatAdapter()
        binding.catsList.adapter = adapter
    }
}