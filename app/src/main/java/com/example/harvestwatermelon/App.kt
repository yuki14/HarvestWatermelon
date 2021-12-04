package com.example.harvestwatermelon

import android.app.Application
import android.content.Context
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        appContext = this
        Coil.setImageLoader(imageLoader)
    }

    companion object {
        @JvmStatic
        lateinit var appContext: Context
    }
}