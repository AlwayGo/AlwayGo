package com.alwaygo.alwaygo.screens.favorite.all_items

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwaygo.alwaygo.core.CoreFragment
import com.alwaygo.alwaygo.data.remote.model.FavoriteItem
import com.alwaygo.alwaygo.databinding.FragmentAllItemsBinding
import com.alwaygo.alwaygo.screens.favorite.FavoritesAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class AllItemsFragment : CoreFragment<FragmentAllItemsBinding>() {

    private var allItems = mutableListOf<FavoriteItem>()
    private lateinit var adapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAllItemsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Set click listener for filter button
        binding?.showAllAddedItemButton?.setOnClickListener {
            showTimeFilterDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = FavoritesAdapter()
        binding?.favoritesRecycler?.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showTimeFilterDialog() {
        val timeFilters = arrayOf(
            "All Items",
            "Added Today",
            "Added This Week",
            "Added This Month",
            "Added Last Month",
            "Custom Date Range"
        )

        AlertDialog.Builder(requireContext())
            .setTitle("Filter by Date Added")
            .setItems(timeFilters) { dialog, which ->
                when (which) {
                    0 -> filterItemsByTimeAdded(null)
                    1 -> filterItemsByTimeAdded(FilterType.TODAY)
                    2 -> filterItemsByTimeAdded(FilterType.THIS_WEEK)
                    3 -> filterItemsByTimeAdded(FilterType.THIS_MONTH)
                    4 -> filterItemsByTimeAdded(FilterType.LAST_MONTH)
                    5 -> showCustomDateRangePicker()
                }
            }
            .show()
    }

    @SuppressLint("SetTextI18n")
    private fun filterItemsByTimeAdded(filterType: FilterType?) {
        val calendar = Calendar.getInstance()

        val filteredItems = when (filterType) {
            FilterType.TODAY -> {
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                val startOfDay = calendar.timeInMillis
                allItems.filter { it.timeAdded >= startOfDay }
            }
            FilterType.THIS_WEEK -> {
                calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                val startOfWeek = calendar.timeInMillis
                allItems.filter { it.timeAdded >= startOfWeek }
            }
            FilterType.THIS_MONTH -> {
                calendar.set(Calendar.DAY_OF_MONTH, 1)
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                val startOfMonth = calendar.timeInMillis
                allItems.filter { it.timeAdded >= startOfMonth }
            }
            FilterType.LAST_MONTH -> {
                calendar.add(Calendar.MONTH, -1)
                calendar.set(Calendar.DAY_OF_MONTH, 1)
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                val startOfLastMonth = calendar.timeInMillis

                val tempCalendar = Calendar.getInstance()
                tempCalendar.set(Calendar.DAY_OF_MONTH, 1)
                tempCalendar.set(Calendar.HOUR_OF_DAY, 0)
                tempCalendar.set(Calendar.MINUTE, 0)
                tempCalendar.set(Calendar.SECOND, 0)
                val endOfLastMonth = tempCalendar.timeInMillis - 1

                allItems.filter { it.timeAdded in startOfLastMonth..endOfLastMonth }
            }
            null -> allItems // Return all items when no filter is applied
            else -> allItems
        }

        // Update your adapter with filtered items
        adapter.submitList(filteredItems)

        // Update item count text
        binding?.countItemText?.text = "${filteredItems.size} items"

        // Update filter text based on selection
        binding?.recentlyAddedText?.text = when (filterType) {
            FilterType.TODAY -> "Added Today"
            FilterType.THIS_WEEK -> "Added This Week"
            FilterType.THIS_MONTH -> "Added This Month"
            FilterType.LAST_MONTH -> "Added Last Month"
            FilterType.CUSTOM -> "Custom Range"
            null -> "Recently Added"
        }
    }

    private fun showCustomDateRangePicker() {
        // Implementation depends on your date picker library
        // For example with Material Date Picker:
        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select Date Range")
            .build()

        dateRangePicker.addOnPositiveButtonClickListener { selection ->
            val startDate = selection.first
            val endDate = selection.second
            filterItemsByCustomDateRange(startDate, endDate)
        }

        dateRangePicker.show(parentFragmentManager, "DATE_RANGE_PICKER")
    }

    @SuppressLint("SetTextI18n")
    private fun filterItemsByCustomDateRange(startDate: Long, endDate: Long) {
        val filteredItems = allItems.filter {
            it.timeAdded in startDate..<endDate + TimeUnit.DAYS.toMillis(1)
        }

        // Update adapter with filtered items
        adapter.submitList(filteredItems)

        // Update item count text
        binding?.countItemText?.text = "${filteredItems.size} items"

        // Update filter text
        binding?.recentlyAddedText?.text = "Custom Date Range"
    }

}