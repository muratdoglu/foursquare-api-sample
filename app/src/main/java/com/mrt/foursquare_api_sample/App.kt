package com.mrt.foursquare_api_sample

import android.app.Application
import android.content.Context
import com.mrt.foursquare_api_sample.module.mainModule
import com.mrt.foursquare_api_sample.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

class App : Application() {

    companion object {
         var STATIC_LOCATION="47.6170,-122.3435"
    }
    override fun onCreate() {
        super.onCreate()
        startKoins(this)
    }
}

fun startKoins(context: Context) {
    startKoin {
        androidLogger()
        androidContext(context)
        modules(listOf(networkModule, mainModule))
    }

}