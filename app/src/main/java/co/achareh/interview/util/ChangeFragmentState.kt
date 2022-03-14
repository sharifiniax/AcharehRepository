package co.achareh.interview.util

sealed class ChangeFragmentState {

    object Empty:ChangeFragmentState()
    object Go:ChangeFragmentState()

}