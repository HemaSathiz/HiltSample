package com.hilt.hiltsampleproject.ui.posts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hilt.hiltsampleproject.R
import com.hilt.hiltsampleproject.databinding.FragmentPostBinding
import com.hilt.hiltsampleproject.model.PostDetailsItem
import com.hilt.hiltsampleproject.network.Status
import com.hilt.hiltsampleproject.ui.detailpost.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_post.*

@AndroidEntryPoint

class PostFragment : Fragment(), PostAdapter.OnClickListener {

    private val postViewModel: PostViewModel by viewModels()
    private lateinit var adapter: PostAdapter
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostAdapter(this)
        rvEmployees.layoutManager = LinearLayoutManager(activity)
        rvEmployees.adapter = adapter

        postViewModel.res.observe(
            viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        progress.visibility = View.GONE
                        rvEmployees.visibility = View.VISIBLE
                        it.data.let { res ->
                            adapter.submitList(res!!)
                        }
                    }
                    Status.LOADING -> {
                        progress.visibility = View.VISIBLE
                        rvEmployees.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progress.visibility = View.GONE
                        rvEmployees.visibility = View.VISIBLE
                        Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }

    override fun onClickPostListner(postDetailsItem: PostDetailsItem) {
        var intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("postdetails", postDetailsItem)
        startActivity(intent)
    }
}
