package com.hilt.hiltsampleproject.ui.detailpost

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hilt.hiltsampleproject.R
import com.hilt.hiltsampleproject.network.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.progress

@AndroidEntryPoint

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        adapter = DetailAdapter()
        rvComments.layoutManager = LinearLayoutManager(this)
        rvComments.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        rvComments.adapter = adapter

        detailViewModel.res.observe(
            this,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        progress.visibility = View.GONE
                        rvComments.visibility = View.VISIBLE
                        it.data.let { res ->
                            adapter.submitList(res!!)
                        }
                    }
                    Status.LOADING -> {
                        progress.visibility = View.VISIBLE
                        rvComments.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progress.visibility = View.GONE
                        rvComments.visibility = View.VISIBLE
                        // Snackbar.make(DetailActivity::class.java, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}
