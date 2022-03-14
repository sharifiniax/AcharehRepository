package co.achareh.interview.data

import com.google.gson.annotations.SerializedName


/**
 *
 *
 * address	"تهران میدان آزادی"
last_name	"اکانت"
first_name	"تست"
gender	""
lat	35.7717503
lng	51.3365315
coordinate_mobile	"989378554841"
coordinate_phone_number	"982136666666"
 *
 *
 *
 *
 *
 */
data class AcharehResponse(

    @SerializedName("address_id")
    val address_id: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("region")
    val region: Region,

    @SerializedName("address")
    val address:String,

    @SerializedName("last_name")
    val last_name:String,

    @SerializedName("first_name")
    val first_name:String,

    @SerializedName("gender")
    val gender:String?,

    @SerializedName("lat")
    val lat:String,

    @SerializedName("lng")
    val lng:String,

    @SerializedName("coordinate_mobile")
    val coordinate_mobile:String,

    @SerializedName("coordinate_phone_number")
    val coordinate_phone_number:String,



)

data class Region(

    @SerializedName("city_object")
    val city_object: CityObject,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("state_object")
    val state_object: StateObject


)

data class CityObject(

    @SerializedName("city_id")
    val city_id: Int,

    @SerializedName("city_name")
    val city_name: String
)

data class StateObject(

    @SerializedName("state_id")
    val state_id: Int,

    @SerializedName("state_name")
    val state_name: String
)