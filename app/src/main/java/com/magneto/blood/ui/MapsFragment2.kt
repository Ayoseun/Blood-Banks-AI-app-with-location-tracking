package com.magneto.blood.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.magneto.blood.R

class MapsFragment2 : Fragment() {

    private val callback = OnStreetViewPanoramaReadyCallback { StreetMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val sanFrancisco = LatLng(37.754130, -122.447129)
        StreetMap.apply {
            setPosition(sanFrancisco)
            isStreetNamesEnabled
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.streetviewpanorama) as  SupportStreetViewPanoramaFragment?
        mapFragment?.getStreetViewPanoramaAsync(callback)
    }
}