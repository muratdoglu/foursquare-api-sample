package com.mrt.foursquare_api_sample.ui.placelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.mrt.foursquare_api_sample.R
import com.mrt.foursquare_api_sample.databinding.FragmentPlaceListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaceListFragment : Fragment() {
    private var binding: FragmentPlaceListBinding? = null
    val vm: PlaceListViewModel by viewModel()
    val placesAdapter: PlacesAdapter? = PlacesAdapter(arrayListOf()) {
        activity?.let { it1 ->
            Navigation.findNavController(it1, R.id.nav_host_fragment).navigate(
                R.id.placeDetailFragment,
                bundleOf("fsq_id" to it.fsq_id)
            )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_place_list, container, false)

        load()
        listeners()
        return binding?.root
    }

    private fun load() {
        binding?.loadingSpinner?.visibility=View.VISIBLE
        binding?.rvPlaces?.layoutManager =  LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding?.rvPlaces?.adapter = placesAdapter
        val args: PlaceListFragmentArgs by navArgs()
        vm.getPlaces(args.text)

    }

    private fun listeners() {
        vm.placesResponse?.observe(viewLifecycleOwner, Observer {
            binding?.loadingSpinner?.visibility=View.GONE
            it.results?.let { it1 -> placesAdapter?.updateN(it1) }
        })
    }

}