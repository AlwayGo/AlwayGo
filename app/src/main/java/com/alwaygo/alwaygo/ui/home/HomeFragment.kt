package com.alwaygo.alwaygo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwaygo.alwaygo.R
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.data.remote.model.ImageModel
import com.alwaygo.alwaygo.extensions.navigateFromParent
import com.alwaygo.alwaygo.databinding.FragmentHomeBinding
import com.alwaygo.alwaygo.retrofit.RetrofitClient
import com.alwaygo.alwaygo.ui.home.adapters.BestDealsViewAdapter
import com.alwaygo.alwaygo.ui.home.adapters.ForYouRecyclerViewAdapter
import com.alwaygo.alwaygo.ui.home.adapters.RecentlyViewedAdapter
import com.alwaygo.alwaygo.ui.home.adapters.SpecialOfferAdapter
import com.alwaygo.alwaygo.ui.home.adapters.TrendingNowViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class HomeFragment : CoreFragment<FragmentHomeBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.swipeRefreshLayout?.isRefreshing = false
        }

        binding?.searchBtn?.setOnClickListener {
            navigateFromParent(R.id.categoryPage)
        }
        forYouSetupRecyclerView()
        forYouFetchImages()
        specialOfferSetupRecyclerView()
        specialOfferFetchImages()
        recentlyViewedSetupRecyclerView()
        recentlyViewedFetchImages()
        trendingNowSetupRecyclerView()
        trendingNowFetchImages()
        bestDealsSetupRecyclerView()
        bestDealsFetchImages()
    }

    private fun forYouSetupRecyclerView() {
        val adapter = ForYouRecyclerViewAdapter(emptyList())
        binding?.forYouRecyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding?.forYouRecyclerView?.adapter = adapter
    }

    private fun forYouFetchImages() {
        RetrofitClient.instance.getImages().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    if (!images.isNullOrEmpty()) {
                        val adapter = ForYouRecyclerViewAdapter(images.take(10))
                        binding?.forYouRecyclerView?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.e("Retrofit Error", "Error loading images", t)
            }
        })
    }

    private fun specialOfferSetupRecyclerView() {
        binding?.specialOfferLiner?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = SpecialOfferAdapter(emptyList())
        binding?.forYouRecyclerView?.adapter = adapter
    }

    private fun specialOfferFetchImages() {
        RetrofitClient.instance.getImages().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    if (!images.isNullOrEmpty()) {
                        val adapter = SpecialOfferAdapter(images.take(5))
                        binding?.specialOfferLiner?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.e("Retrofit Error", "Error loading images", t)
            }
        })
    }

    private fun recentlyViewedSetupRecyclerView() {
        val adapter = RecentlyViewedAdapter(emptyList())
        binding?.recentlyViewRecycler?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding?.recentlyViewRecycler?.adapter = adapter
    }

    private fun recentlyViewedFetchImages() {
        RetrofitClient.instance.getImages().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    if (!images.isNullOrEmpty()) {
                        val adapter = RecentlyViewedAdapter(images.take(10))
                        binding?.recentlyViewRecycler?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.e("Retrofit Error", "Error loading images", t)
            }
        })
    }

    private fun trendingNowSetupRecyclerView() {
        val adapter = TrendingNowViewAdapter(emptyList())
        binding?.trendingNowRecycler?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding?.trendingNowRecycler?.adapter = adapter
    }

    private fun trendingNowFetchImages() {
        RetrofitClient.instance.getImages().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    if (!images.isNullOrEmpty()) {
                        val adapter = TrendingNowViewAdapter(images.take(10))
                        binding?.trendingNowRecycler?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.e("Retrofit Error", "Error loading images", t)
            }
        })
    }

    private fun bestDealsSetupRecyclerView() {
        val adapter = BestDealsViewAdapter(emptyList())
        binding?.bestDealsRecycler?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding?.bestDealsRecycler?.adapter = adapter
    }

    private fun bestDealsFetchImages() {
        RetrofitClient.instance.getImages().enqueue(object : Callback<List<ImageModel>> {
            override fun onResponse(call: Call<List<ImageModel>>, response: Response<List<ImageModel>>) {
                if (response.isSuccessful) {
                    val images = response.body()
                    if (!images.isNullOrEmpty()) {
                        val adapter = BestDealsViewAdapter(images.take(10))
                        binding?.bestDealsRecycler?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<ImageModel>>, t: Throwable) {
                Log.e("Retrofit Error", "Error loading images", t)
            }
        })
    }
}