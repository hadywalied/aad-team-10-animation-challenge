package com.aad.alc4.team10.animatedweatherapp.ui.main.country_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aad.alc4.team10.animatedweatherapp.R
import com.aad.alc4.team10.animatedweatherapp.model.Country
import com.aad.alc4.team10.animatedweatherapp.model.Region
import com.aad.alc4.team10.animatedweatherapp.ui.main.region_screen.RegionFragmentDirections

class CountryFragment : Fragment(), MyCountryRecyclerViewAdapter.OnCountryClicked {

    private var columnCount = 2

    lateinit var region: Region
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        region = arguments?.getParcelable<Region>("exportedRegion")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    MyCountryRecyclerViewAdapter(
                        region.countries!!,
                        this@CountryFragment
                    )
            }
        }
        return view
    }

    override fun onClick(position: Int) {
        startCountriesScreen(region.countries!!.get(position))
    }

    private fun startCountriesScreen(country: Country) = CountryFragmentDirections
        .actionCountryFragmentToCityFragment(country)
        .let { activity?.findNavController(R.id.nav_host_fragment)?.navigate(it) }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            CountryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
