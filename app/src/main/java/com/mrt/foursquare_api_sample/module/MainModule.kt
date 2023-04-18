package com.mrt.foursquare_api_sample.module

import android.util.Log
import com.mrt.foursquare_api_sample.data.AllRepository
import com.mrt.foursquare_api_sample.network.RestInterface
import com.mrt.foursquare_api_sample.ui.detail.PlaceDetailViewModel
import com.mrt.foursquare_api_sample.ui.placelist.PlaceListViewModel
import com.mrt.foursquare_api_sample.ui.search.SearchViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import java.util.concurrent.TimeUnit

val mainModule = module {
    single { AllRepository(get()) }
     viewModel { SearchViewModel( get()) }
    viewModel { PlaceListViewModel( get()) }
    viewModel { PlaceDetailViewModel( get()) }
}

val networkModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor { Log.d("API", it) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory<okhttp3.OkHttpClient> { networkSettings() }
    single {
        OkHttpClient.Builder()
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.foursquare.com/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(RestInterface::class.java) }

}

fun networkSettings(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    var okhttp = OkHttpClient.Builder().protocols(arrayListOf(Protocol.HTTP_1_1))
        .connectTimeout(25, TimeUnit.SECONDS)
        .writeTimeout(25, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "fsq3HX70DkOGOoRoRdVckz+ftU3psuluLABS8Y23x0QJFS0=")
                .build()
            chain.proceed(request)
        }.build()
    return okhttp
}