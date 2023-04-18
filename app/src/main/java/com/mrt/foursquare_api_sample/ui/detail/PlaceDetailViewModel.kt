package com.mrt.foursquare_api_sample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrt.foursquare_api_sample.data.AllRepository
import com.mrt.foursquare_api_sample.data.PlaceDetailResponse
import com.mrt.foursquare_api_sample.data.PlacesResponse

class PlaceDetailViewModel(
    val repo: AllRepository
) : ViewModel() {
    private val _placeDetailResponse = MutableLiveData<PlaceDetailResponse>()
    val placeDetailResponse: LiveData<PlaceDetailResponse> = _placeDetailResponse

    fun getPlaceDetail(id:String){
        repo.getPlaceDetail(id,{
            _placeDetailResponse.postValue(it)
        },{

        },{

        })
    }
}