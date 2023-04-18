package com.mrt.foursquare_api_sample.ui.placelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrt.foursquare_api_sample.data.AllRepository
import com.mrt.foursquare_api_sample.data.PlacesResponse


class PlaceListViewModel(
    val repo: AllRepository
) : ViewModel() {
    private val _placesResponse = MutableLiveData<PlacesResponse>()
    val placesResponse: LiveData<PlacesResponse> = _placesResponse


    fun getPlaces(text:String){
        repo.getPlaces(text,{
            _placesResponse.postValue(it)
        },{

        },{

        })
    }

}