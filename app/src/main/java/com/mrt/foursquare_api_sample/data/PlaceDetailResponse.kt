package com.mrt.foursquare_api_sample.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlaceDetailResponse : Serializable {
    @SerializedName("fsq_id") val fsq_id : String?=null
    @SerializedName("categories") val categories : List<Categories>?=null
    @SerializedName("chains") val chains : List<String>?=null
    @SerializedName("geocodes") val geocodes : Geocodes?=null
    @SerializedName("link") val link : String?=null
    @SerializedName("location") val location : Location?=null
    @SerializedName("name") val name : String?=null
    @SerializedName("timezone") val timezone : String?=null
}
data class Location (

    @SerializedName("address") val address : String,
    @SerializedName("country") val country : String,
    @SerializedName("cross_street") val cross_street : String,
    @SerializedName("dma") val dma : String,
    @SerializedName("formatted_address") val formatted_address : String,
    @SerializedName("locality") val locality : String,
    @SerializedName("postcode") val postcode : Int,
    @SerializedName("region") val region : String
)

data class Categories (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("icon") val icon : Icon
)
data class Drop_off (

    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)

data class Geocodes (

    @SerializedName("drop_off") val drop_off : Drop_off,
    @SerializedName("main") val main : Main,
    @SerializedName("roof") val roof : Roof
)
data class Main (

    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)

data class Roof (

    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double
)