package io.github.mohamedisoliman.softxpert.ui

import androidx.recyclerview.widget.RecyclerView


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.mohamedisoliman.softxpert.R
import kotlinx.android.synthetic.main.activity_main.*
import java.net.UnknownHostException

class CarsActivity : AppCompatActivity() {


    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CarsViewModel::class.java)
    }
    private val adapter = CarsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        viewModel.loadCars()
        viewModel.carsObserver({
            adapter.showData(it)
        }, {
            when (it) {
                is UnknownHostException -> Toast.makeText(
                    this,
                    "No Internet connection!",
                    Toast.LENGTH_SHORT
                ).show()
                else -> Toast.makeText(
                    this,
                    "Something wrong happend!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        carsRecyclerView.layoutManager = layoutManager
        carsRecyclerView.setHasFixedSize(true)
        carsRecyclerView.adapter = adapter
        carsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        carsRecyclerView.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadCars()
            }
        })
    }
}
