package co.achareh.interview.ui.fillform.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.achareh.interview.R
import co.achareh.interview.databinding.FragmentMapsBinding
import co.achareh.interview.ui.fillform.FormViewModel
import co.achareh.interview.util.RequestState

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.flow.collect



class MapsFragment : Fragment(), GoogleMap.OnCameraIdleListener {

    companion object {
        val TEHRAN_KAJ = LatLng(35.7817, 51.3747)
    }

    private lateinit var map: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TEHRAN_KAJ, 16F))
        googleMap.setOnCameraIdleListener(this)
        googleMap.setMaxZoomPreference(17F)
        googleMap.setMinZoomPreference(12F)

    }

    private lateinit var binding: FragmentMapsBinding
    private lateinit var viewModel: FormViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= activity?.let { ViewModelProvider(it).get(FormViewModel::class.java) }!!

        binding = FragmentMapsBinding.inflate(layoutInflater, container, false)
        binding.viewModel=viewModel
        lifecycleScope.launchWhenStarted {
            viewModel.requestOrder.collect {
                when (it) {
                    RequestState.Empty -> {

                    }
                    RequestState.Loading -> {
                        binding.button.isEnabled = false
                        binding.progressBar.visibility=View.VISIBLE
                        binding.textView3.visibility=View.VISIBLE
                        binding.progressBar.isClickable=false

                    }
                    RequestState.Error -> {
                        Snackbar.make(
                            binding.root,
                            "خطای اینترنتی رخ داده بعدا تلاش کن",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        binding.button.isEnabled = true
                        binding.progressBar.visibility=View.INVISIBLE
                        binding.textView3.visibility=View.INVISIBLE
                    }
                    RequestState.Success -> {
                        Snackbar.make(
                            binding.root,
                            "آدرس شما با موفقیت ثبت شد",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_mapsFragment_to_showDataFragment)
                        viewModel.requestOrder.value=RequestState.Empty
                    }


                }
            }
        }
        return binding.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    //when agent screen is Idle
    override fun onCameraIdle() {
        viewModel.position =
            LatLng(map.cameraPosition.target.latitude, map.cameraPosition.target.longitude)
    }
}