package uz.texnopos.nasya.utils

sealed class State<out T> {
    object LoadingState : State<Nothing>()
    data class SuccessState<T>(var data: T) : State<T>()
    data class ErrorState(var exception: Throwable) : State<Nothing>()

}