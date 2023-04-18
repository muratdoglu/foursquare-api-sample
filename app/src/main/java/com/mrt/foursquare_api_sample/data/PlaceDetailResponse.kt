package com.mrt.foursquare_api_sample.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlacesDetailResponse : Serializable {
    var results: ArrayList<PlacesBody>? = null
}


