package com.alwaygo.alwaygo.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.data.remote.model.ImageModel
import com.bumptech.glide.Glide

class BestDealsViewAdapter(private val productList: List<ImageModel>) :
    RecyclerView.Adapter<BestDealsViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productBrand: TextView = itemView.findViewById(R.id.product_brand)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        Glide.with(holder.itemView.context)
            .load(product.download_url)
            .into(holder.productImage)

        // Hazırda yalnız şəkil API istifadə edirik, ona görə digər məlumatlar sabit qoyulub
        holder.productBrand.text = "Brand ${product.id}"
        holder.productName.text = "Product Name ${product.id}"
        holder.productPrice.text = "₼ ${position * 10 + 5}.99"
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}