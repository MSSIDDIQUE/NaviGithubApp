package com.baymax.navigithubapp.ui.activity.main_activity.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baymax.navigithubapp.api.response.PullRequest
import com.baymax.navigithubapp.databinding.PullRequestItemBinding
import com.baymax.navigithubapp.utils.toTimeAgo

class PrListAdapter(
    private val list: ArrayList<PullRequest>
) : RecyclerView.Adapter<PrListAdapter.PrItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrItemViewHolder {
        val binding =
            PullRequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PrItemViewHolder(
        private val binding: PullRequestItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PullRequest) {
            binding.apply {
                prData = data
                createdAt.text = "Created on \n ${data.createdAt.toTimeAgo()?:data.createdAt}"
                closedAt.text = "Closed on \n ${data.closedAt.toTimeAgo()?:data.closedAt}"
            }
        }
    }
}