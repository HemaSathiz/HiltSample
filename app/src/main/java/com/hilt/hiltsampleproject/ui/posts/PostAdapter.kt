package com.hilt.hiltsampleproject.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hilt.hiltsampleproject.R
import com.hilt.hiltsampleproject.model.PostDetailsItem
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.android.synthetic.main.item_posts.view.*

@FragmentScoped
class PostAdapter(val listner : OnClickListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<PostDetailsItem>() {
        override fun areItemsTheSame(oldItem: PostDetailsItem, newItem: PostDetailsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostDetailsItem, newItem: PostDetailsItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<PostDetailsItem>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_posts,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {
            tvPosts.text = "${item.body}"
        }
        holder.itemView.setOnClickListener { listner.onClickPostListner(item) }
    }

    interface OnClickListener {
        fun onClickPostListner(postDetailsItem: PostDetailsItem)
    }
}
