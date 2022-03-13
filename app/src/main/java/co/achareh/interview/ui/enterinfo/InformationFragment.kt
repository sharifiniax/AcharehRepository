package co.achareh.interview.ui.enterinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import co.achareh.interview.R
import co.achareh.interview.databinding.FragmentInformationBinding


class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_information,container,false)
        binding.nextButtonDown.setOnClickListener {
            findNavController().navigate(R.id.action_informationFragment_to_mapsFragment)

        }
        return binding.root!!
    }



}