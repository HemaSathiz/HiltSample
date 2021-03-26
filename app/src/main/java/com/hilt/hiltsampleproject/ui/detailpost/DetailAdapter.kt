package com.hilt.hiltsampleproject.ui.detailpost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hilt.hiltsampleproject.R
import com.hilt.hiltsampleproject.model.CommentsItem
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.android.synthetic.main.item_comments.view.*

@ActivityScoped
class DetailAdapter : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<CommentsItem>() {
        override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<CommentsItem>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.item_comments,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {
            txtName.text ="Name : "+"${item.name}"
            txtEmailId.text = "Email : "+"${item.email}"
            txtBody.text = "Message : "+"${item.body}"
        }
    }
}
