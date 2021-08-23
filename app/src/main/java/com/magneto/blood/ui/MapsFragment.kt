package com.magneto.blood.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.location.Location
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.magneto.blood.R

class MapsFragment : Fragment() {



    var long : Double = 3.3792
    var lat : Double = 6.5244

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        //6.5244° N, 3.3792° E
        setUpLocationListener()


            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            // for getting the current location update after every 2 seconds with high accuracy
            val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        for (location in locationResult.locations) {
                            lat = location.latitude

                            long = location.longitude

                            val lagos = LatLng(lat, long)
                            googleMap.apply {
                                addMarker(MarkerOptions().position(lagos).title("Marker in Sydney"))
                                moveCamera(CameraUpdateFactory.newLatLng(lagos))



                            }

                        }
                        // Few more things we can do here:
                        // For example: Update the location of user on server
                    }
                },
                Looper.myLooper()
            )






    }

    override fun onStart() {
        super.onStart()
        setUpLocationListener()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        run {
            val w: Window = requireActivity().window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )

        }

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocationListener() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        // for getting the current location update after every 2 seconds with high accuracy
        val locationRequest = LocationRequest().setInterval(2000).setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        lat = location.latitude
                        Toast.makeText(activity,"$lat",Toast.LENGTH_SHORT).show()
                        long = location.longitude
                    }
                    // Few more things we can do here:
                    // For example: Update the location of user on server
                }
            },
            Looper.myLooper()
        )
    }



}