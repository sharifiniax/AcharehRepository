package co.achareh.interview.util

sealed class RequestState{

    object Loading:RequestState()
    object Success:RequestState()
    object Error:RequestState()
    object Empty:RequestState()

}
