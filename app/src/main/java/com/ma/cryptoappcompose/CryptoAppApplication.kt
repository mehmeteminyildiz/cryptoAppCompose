package com.ma.cryptoappcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
created by Mehmet E. Yıldız
 **/
@HiltAndroidApp
class CryptoAppApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}