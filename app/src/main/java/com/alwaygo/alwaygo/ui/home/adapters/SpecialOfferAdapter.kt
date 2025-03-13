package com.alwaygo.alwaygo.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.data.remote.model.ImageModel
import com.bumptech.glide.Glide

class SpecialOfferAdapter(private val imageList: List<ImageModel>) :
    RecyclerView.Adapter<SpecialOfferAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_special_offer_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = imageList[position]

        Glide.with(holder.itemView.context)
            .load(image.download_url)
            .into(holder.categoryImage)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
