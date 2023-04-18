package com.mrt.foursquare_api_sample.network


import com.mrt.foursquare_api_sample.data.PlaceDetailResponse
import com.mrt.foursquare_api_sample.data.PlacesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RestInterface {

    @Headers("Content-Type: application/json")
    @GET(" /v3/places/search")
    fun getPlaces(
        @Query("query") query: String?,
        @Query("ll") ll: String
    ): Call<PlacesResponse?>?

    @Headers("Content-Type: application/json")
    @GET(" /v3/places/{id}")
    fun getPlaceDetail(
        @Path("id") id: String?
    ): Call<PlaceDetailResponse?>?

}