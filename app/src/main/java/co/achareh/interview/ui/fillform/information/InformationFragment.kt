package co.achareh.interview.ui.fillform.information

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.achareh.interview.R
import co.achareh.interview.databinding.FragmentInformationBinding
import co.achareh.interview.ui.fillform.FormViewModel
import co.achareh.interview.util.ChangeFragmentState
import co.achareh.interview.util.FillFormState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect


class InformationFragment : Fragment() {

    private lateinit var viewModel:FormViewModel
    private lateinit var binding: FragmentInformationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= activity?.let { ViewModelProvider(it).get(FormViewModel::class.java) }!!
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_information,container,false)
        binding.viewmodel=viewModel

        validateFormState()

        lifecycleScope.launchWhenStarted {

            viewModel.stateForm.collect {
                when(it){
                    FillFormState.Empty ->{}
                    FillFormState.Loading ->{}
                    FillFormState.Success ->{
                        findNavController().navigate(R.id.action_informationFragment_to_mapsFragment)
                        viewModel.stateForm.value=FillFormState.Empty
                        viewModel.validStateFirstName=false
                        viewModel.validStateLastName=false
                        viewModel.validStateCellPhone=false
                        viewModel.validStatePhone=false
                        viewModel.validStateAddress=false
                    }
                    FillFormState.ErrorField("خطا در ورود اطلاعات") -> {
                        Snackbar.make(binding.root,
                            "خطا در ورود اطلاعات",
                            Snackbar.LENGTH_SHORT
                            ).show()
                    }

                    else -> {}
                }            }
        }


        return binding.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        resetForm()
        super.onViewCreated(view, savedInstanceState)
    }




    private fun validateFormState() {

        binding.firstName.addTextChangedListener {


                if (binding.firstName.text.length < 3) {
                    viewModel.validStateFirstName=false
                    binding.firstName.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_error_state, 0, 0, 0
                    )
                } else {
                    viewModel.validStateFirstName=true
                    binding.firstName.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_seccess_state, 0, 0, 0
                    )
                }

        }
        binding.lastName.addTextChangedListener {

                if (binding.lastName.text.length < 3) {
                    viewModel.validStateLastName=false

                    binding.lastName.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_error_state, 0, 0, 0
                    )
                } else {
                    viewModel.validStateLastName=true
                    binding.lastName.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_seccess_state, 0, 0, 0
                    )
                }

        }
        binding.cellphone.addTextChangedListener {

                if (binding.cellphone.text.length < 11
                    || !binding.cellphone.text.startsWith("0")) {
                    viewModel.validStateCellPhone=false

                    binding.cellphone.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_error_state, 0, 0, 0
                    )
                } else {
                    viewModel.validStateCellPhone=true

                    binding.cellphone.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_seccess_state, 0, 0, 0
                    )
                }

        }
        binding.phone.addTextChangedListener {

                if (binding.phone.text.length < 11
                    || !binding.phone.text.startsWith("0")) {
                    viewModel.validStatePhone=false

                    binding.phone.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_error_state, 0, 0, 0
                    )
                } else {
                    viewModel.validStatePhone=true

                    binding.phone.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_seccess_state, 0, 0, 0
                    )
                }

        }
        binding.address.addTextChangedListener {

                if (binding.address.text.length < 10) {
                    viewModel.validStateAddress=false

                    binding.address.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_error_state, 0, 0, 0
                    )
                } else {
                    viewModel.validStateAddress=true
                    binding.address.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_seccess_state, 0, 0, 0
                    )
                }

        }

        }

    private fun resetForm(){


        viewModel.cellPhone=""
        viewModel.phone=""
        viewModel.firstName=""
        viewModel.gender=false
        viewModel.address=""
        viewModel.lastName=""

    }



    }


