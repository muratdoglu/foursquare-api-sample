package com.mrt.foursquare_api_sample.data

import com.mrt.foursquare_api_sample.App
import com.mrt.foursquare_api_sample.network.RestInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllRepository(
    val restInterface: RestInterface
) {
    inline fun getPlaces(text:String,
                             crossinline successHandler: (PlacesResponse) -> Unit,
                             crossinline failureHandler: (Throwable?) -> Unit,
                             crossinline errorHandler: () -> Unit
    ) {
        restInterface.getPlaces(text,App.STATIC_LOCATION)
            ?.enqueue(object : Callback<PlacesResponse?> {
                override fun onResponse(
                    call: Call<PlacesResponse?>,
                    response: Response<PlacesResponse?>
                ) {
                    response.let {
                        if (response.isSuccessful) {
                            response.body()?.let { it1 -> successHandler(it1) } ?: kotlin.run {
                                errorHandler()
                            }
                        } else {
                            errorHandler()
                        }
                    }
                }

                override fun onFailure(call: Call<PlacesResponse?>?, t: Throwable?) {
                    failureHandler(t)
                }
            })
    }

    inline fun getPlaceDetail(id:String,
                         crossinline successHandler: (PlaceDetailResponse) -> Unit,
                         crossinline failureHandler: (Throwable?) -> Unit,
                         crossinline errorHandler: () -> Unit
    ) {
        restInterface.getPlaceDetail(id)
            ?.enqueue(object : Callback<PlaceDetailResponse?> {
                override fun onResponse(
                    call: Call<PlaceDetailResponse?>,
                    response: Response<PlaceDetailResponse?>
                ) {
                    response.let {
                        if (response.isSuccessful) {
                            response.body()?.let { it1 -> successHandler(it1) } ?: kotlin.run {
                                errorHandler()
                            }
                        } else {
                            errorHandler()
                        }
                    }
                }

                override fun onFailure(call: Call<PlaceDetailResponse?>?, t: Throwable?) {
                    failureHandler(t)
                }
            })
    }


}