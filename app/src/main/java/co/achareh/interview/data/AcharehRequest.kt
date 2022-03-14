package co.achareh.interview.data

import com.google.gson.annotations.SerializedName




data class AcharehRequest(

//    @SerializedName("address")
    val address: String,

//    @SerializedName("coordinate_mobile")
    val coordinate_mobile: String,

//    @SerializedName("coordinate_phone_number")
    val coordinate_phone_number: String,

//    @SerializedName("first_name")
    val first_name: String,

//    @SerializedName("gender")
    val gender: String,

//    @SerializedName("last_name")
    val last_name: String,

//    @SerializedName("lat")
    val lat: String,

//    @SerializedName("lng")
    val lng: String,

//    @SerializedName("address")
    val region: Int
)
