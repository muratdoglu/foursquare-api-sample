package com.mrt.foursquare_api_sample.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mrt.foursquare_api_sample.R
import com.mrt.foursquare_api_sample.data.PlaceDetailResponse
import com.mrt.foursquare_api_sample.databinding.FragmentPlaceDetailBinding
import com.mrt.foursquare_api_sample.databinding.FragmentPlaceListBinding
import com.mrt.foursquare_api_sample.ui.placelist.PlaceListFragmentArgs
import com.mrt.foursquare_api_sample.ui.placelist.PlaceListViewModel
import com.mrt.foursquare_api_sample.ui.placelist.PlacesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaceDetailFragment : Fragment() {
    var dialog: BottomSheetDialog? = null

    private var binding: FragmentPlaceDetailBinding? = null
    val vm: PlaceDetailViewModel by viewModel()
     var mMap: com.google.android.gms.maps.GoogleMap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_place_detail, container, false)
        load()
        listeners()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun listeners() {
        vm.placeDetailResponse?.observe(viewLifecycleOwner, Observer {
           var place= it.geocodes?.main?.latitude?.let { it1 ->
               it.geocodes?.main?.longitude?.let { it2 ->
                   LatLng(
                       it1,
                       it2
                   )
               }
           }
            mMap?.addMarker(place?.let { it1 -> MarkerOptions().position(it1).title(it.name) })

            mMap?.addMarker(place?.let { it1 -> MarkerOptions().position(LatLng(47.6170,-122.3435)).title("Center") })
            mMap?.moveCamera(CameraUpdateFactory.newLatLng(place))

            showDetailDialog(it)
        })

    }

    private fun showDetailDialog(it: PlaceDetailResponse?) {
        dialog?.setContentView(R.layout.bottom_place_detail)
        dialog?.findViewById<TextView>(R.id.tvName)?.text=it?.name
        dialog?.findViewById<TextView>(R.id.tvAddress)?.text=it?.location?.formatted_address
        dialog?.findViewById<TextView>(R.id.tvTimeZone)?.text=it?.timezone
        dialog?.findViewById<TextView>(R.id.tvCategory)?.text=it?.categories?.get(0)?.name
        dialog?.show()
    }

    private fun load() {
        dialog = activity?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }
        val args: PlaceDetailFragmentArgs by navArgs()
        vm.getPlaceDetail(args.fsqId)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        mMap=googleMap
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(12f))
    }

}