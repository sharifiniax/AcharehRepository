package co.achareh.interview.ui.fillform

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.achareh.interview.data.AcharehRequest
import co.achareh.interview.data.AcharehResponse
import co.achareh.interview.repositories.AcharehRepository
import co.achareh.interview.util.ChangeFragmentState
import co.achareh.interview.util.FillFormState
import co.achareh.interview.util.RequestState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FormViewModel : ViewModel() {

    //position order
    lateinit var position: LatLng

    //repository for communication
    private val repository = AcharehRepository()

    //Observable state of request
    private val _requestOrder = MutableStateFlow<RequestState>(RequestState.Empty)
    val requestOrder = _requestOrder


    //States of action's nextToMap method
    //The actions will do in informationFragment
    private val _stateForm = MutableStateFlow<FillFormState>(FillFormState.Empty)
    val stateForm = _stateForm

    // State for navigate ShowDataFragment to information fragment
    private val _rout = MutableStateFlow<ChangeFragmentState>(ChangeFragmentState.Empty)
    val rout = _rout




    //States of validation form
    var validStateFirstName: Boolean = false
    var validStateLastName: Boolean = false
    var validStateCellPhone: Boolean = false
    var validStatePhone: Boolean = false
    var validStateAddress: Boolean = false


    //Fields form
    var firstName: String = ""
    var lastName: String = ""
    var cellPhone: String = ""
    var phone: String = ""
    var address: String = ""
    var gender: Boolean = false


    //this method change the states of _stateForm
    fun nextToMap() {

        _stateForm.value = FillFormState.Loading

        var valid = true

        if (!validStateFirstName) {
            valid = false
        }

        if (!validStateLastName) {
            valid = false
        }

        if (!validStateCellPhone) {
            valid = false
        }

        if (!validStatePhone) {
            valid = false
        }

        if (!validStateAddress) {
            valid = false
        }


        if (valid) {
            _stateForm.value = FillFormState.Success
        } else {
            _stateForm.value = FillFormState.ErrorField("خطا در ورود اطلاعات")
        }

    }


    //send request to Achareh for submit Order
    fun submit() {
        _requestOrder.value = RequestState.Loading
        val model = AcharehRequest(
            address,
            cellPhone,
            phone,
            firstName,
            gender = if (gender) "Male" else "Female",
            lastName,
            position.latitude.toString(),
            position.longitude.toString(),
            1
        )

        viewModelScope.launch(Dispatchers.IO) {
            var sucess: Boolean
            kotlin.runCatching {
                sucess = repository.sendOrder(model)

                if (sucess) {

                    _requestOrder.value = RequestState.Success

                }

            }.onFailure {
                it.message?.let { it1 -> Log.d("http", it1) }
                _requestOrder.value = RequestState.Error
            }

        }


    }


    fun addAddress() {
        _rout.value = ChangeFragmentState.Go
    }

    var listOrders= MutableLiveData<List<AcharehResponse>>()
    val listState=MutableStateFlow<RequestState>(RequestState.Empty)
    fun getList() {
        listState.value=RequestState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            kotlin.runCatching {
                val list = repository.getList()
                listOrders.postValue(list!!)
                listState.value=RequestState.Success
            }.onFailure {
                listState.value=RequestState.Error
            }


        }


    }


}