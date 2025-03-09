package com.alwaygo.alwaygo.splashscreen

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {

    private val _screenIndex = MutableLiveData<Int>()
    val screenIndex: LiveData<Int> get() = _screenIndex

    private val handler = Handler(Looper.getMainLooper())
    private var currentIndex = 0

    init {
        startSplash()
    }

    private fun startSplash() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (currentIndex < 3) {
                    _screenIndex.postValue(currentIndex++)
                    handler.postDelayed(this, 1000)
                }
            }
        }, 2000)
    }
}