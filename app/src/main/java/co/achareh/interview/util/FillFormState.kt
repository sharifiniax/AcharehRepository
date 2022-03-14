package co.achareh.interview.util

sealed class FillFormState{

    object Empty:FillFormState() // When start app this state will be set
    object Loading:FillFormState() // Loading time or processing state
    data class ErrorField(val message:String):FillFormState() // error of validation form
    object Success:FillFormState() //When agent fills form successfully

}
