package co.achareh.interview.ui.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import co.achareh.interview.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(),GoogleMap.OnCameraIdleListener {
    companion object{
        val TEHRAN_KAJ = LatLng(35.7817, 51.3747)
    }
    private lateinit var position: LatLng
    lateinit var map:GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        map=googleMap
        googleMap.mapType=GoogleMap.MAP_TYPE_NORMAL
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TEHRAN_KAJ, 16F))
        googleMap.setOnCameraIdleListener(this)
        googleMap.setMaxZoomPreference(17F)
        googleMap.setMinZoomPreference(12F)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onCameraIdle() {
        position=LatLng(map.cameraPosition.target.latitude,map.cameraPosition.target.longitude)
//        Toast.makeText(context,position.toString(),Toast.LENGTH_LONG).show()
    }
}