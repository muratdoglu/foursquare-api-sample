package com.mrt.foursquare_api_sample.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlacesResponse : Serializable {
    var results: ArrayList<PlacesBody>? = null
}

class PlacesBody {
    var categories: ArrayList<Category>? = null
    var distance: Int? = null
    var name: String? = null
    var fsq_id: String? = null

}

class Category {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("icon")
    var icon: Icon? = Icon()
}

data class Icon(

    @SerializedName("prefix") var prefix: String? = null,
    @SerializedName("suffix") var suffix: String? = null

)

