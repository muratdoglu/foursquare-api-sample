package com.mrt.foursquare_api_sample.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mrt.foursquare_api_sample.R
import com.mrt.foursquare_api_sample.data.Category
import com.mrt.foursquare_api_sample.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private var binding: FragmentSearchBinding? = null
class SearchFragment : Fragment() {
    val vm: SearchViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
       ui()
        listeners()
        return binding?.root
    }

    private fun ui() {
        binding?.btnSearch?.setOnClickListener {
            if(binding?.autName?.text.toString().isNotEmpty()){
                activity?.let { it1 ->
                    Navigation.findNavController(it1, R.id.nav_host_fragment).navigate(
                        R.id.placeListFragment,
                        bundleOf("text" to binding?.autName?.text.toString())
                    )
                }
            }

        }
        binding?.autName?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    private fun setAdapter(categories: ArrayList<Category>?) {
        val strList: ArrayList<String> = categories?.map { it.name } as ArrayList<String>
        ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, strList).also { adapter ->
            binding?.autName?.setAdapter(adapter)
            if ((categories?.size ?: 0) > 0){
                binding?.autName?.showDropDown()
            }

        }
    }

    private fun listeners() {

    }

}