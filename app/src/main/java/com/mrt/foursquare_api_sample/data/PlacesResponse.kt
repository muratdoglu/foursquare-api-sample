package com.mrt.foursquare_api_sample.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CategoriesResponse: Serializable {
    var results:  ArrayList<CategoriesBody>? = null
}

class CategoriesBody {
    var categories: ArrayList<Category>? = null
}

class Category {
    @SerializedName("id"   ) var id   : Int?    = null
    @SerializedName("name" ) var name : String? = null
    @SerializedName("icon" ) var icon : Icon?   = Icon()
}

data class Icon (

    @SerializedName("prefix" ) var prefix : String? = null,
    @SerializedName("suffix" ) var suffix : String? = null

)

