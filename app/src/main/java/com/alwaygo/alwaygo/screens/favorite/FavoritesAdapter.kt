package com.alwaygo.alwaygo.screens.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alwaygo.alwaygo.data.remote.model.FavoriteItem
import com.alwaygo.alwaygo.databinding.ItemsFavoritesRecyclerBinding

class FavoritesAdapter : ListAdapter<FavoriteItem,FavoritesAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    private var items = listOf<FavoriteItem>()

    inner class FavoriteViewHolder(private val binding: ItemsFavoritesRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteItem) {
            binding.tvBrandName.text = item.name
            binding.tvPrice.text = item.price.toString()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavoritesAdapter.FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsFavoritesRecyclerBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteItem>() {
            override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}