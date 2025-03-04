package com.alwaygo.alwaygo.splashscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SplashAdapter(private val layouts: List<Int>):
    RecyclerView.Adapter<SplashAdapter.SplashViewHolder>() {

    inner class SplashViewHolder(private val binding: View): RecyclerView.ViewHolder(binding)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SplashAdapter.SplashViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return SplashViewHolder(view)
    }

    override fun onBindViewHolder(holder: SplashAdapter.SplashViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return layouts.size
    }

    override fun getItemViewType(position: Int): Int {
        return layouts[position]
    }
}