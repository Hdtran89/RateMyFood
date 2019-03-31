package com.example.ratemyfood.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.ratemyfood.R
import com.example.ratemyfood.data.model.BusinessCard
import com.example.ratemyfood.util.Constants
import com.example.ratemyfood.util.InfiniteScrollListener
import com.example.ratemyfood.util.ViewModelFactory
import com.example.ratemyfood.viewmodel.BusinessCardViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    var searchAdapter = SearchAdapter()
    private lateinit var businessCardViewModel: BusinessCardViewModel
    var currentPage = 0
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        AndroidInjection.inject(this)
        searchText = findViewById(R.id.searchBarText)
        initializeRecycler()
        businessCardViewModel = ViewModelProviders.of(this, viewModelFactory).get(
            BusinessCardViewModel::class.java)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        searchText.setOnClickListener {
            searchText.text.clear()
        }

        searchText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                progressBar.visibility = View.VISIBLE
                loadData(v.text.toString())
                true
            }
            false
        }

        businessCardViewModel.businessCardResult().observe(this, Observer<List<BusinessCard>>{
            if (it != null) {
                val position = searchAdapter.itemCount
                searchAdapter.addBussinessCard(it)
                recyclerView.adapter = searchAdapter
                recyclerView.scrollToPosition(position - Constants.LIST_SCROLLING)
            }
        })

        businessCardViewModel.businessCardError().observe(this, Observer<String> {
            if (it != null) {
                Log.e("Error" ,it)
            }
        })

        businessCardViewModel.businessCardLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView = findViewById(R.id.recyclerView)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({ loadData("") }, gridLayoutManager))
        }
    }

    fun loadData(search: String ) {
        businessCardViewModel.fetchBusinessCards(Constants.LIMIT, currentPage * Constants.OFFSET, search)
        currentPage++
    }

    override fun onDestroy() {
        businessCardViewModel.disposeElements()
        super.onDestroy()
    }
}