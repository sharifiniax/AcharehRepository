package co.achareh.interview.models



class UserRequest(
    val region:Int,
    val address:String,
    val lat:Double,
    val lng:Double,
    val coordinateMobile:Int,
    val coordinatePhoneNumber:Int,
    val firstName:String,
    val lastName:String,
    val gender:Gender
    )

enum class Gender{
    Male,Female
}