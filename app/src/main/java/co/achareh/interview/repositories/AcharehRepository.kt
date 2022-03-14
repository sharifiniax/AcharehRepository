package co.achareh.interview.repositories

import android.util.Log
import co.achareh.interview.data.AcharehRequest
import co.achareh.interview.data.AcharehResponse
import co.achareh.interview.network.RetrofitService

class AcharehRepository {

    private val acharehApi = RetrofitService.getInstance()

    suspend fun getList() : List<AcharehResponse>?{
        var list:List<AcharehResponse>?=null
       val response = acharehApi.getOrder()

        if (response.isSuccessful){
            list=response.body()
        }
        return list
    }

    suspend fun sendOrder(order:AcharehRequest):Boolean{

        val response=acharehApi.sendOrder(order)

        if (response.isSuccessful){

            return true

        }


        return false
    }

    suspend fun gst(){

        acharehApi.gst()
    }


}