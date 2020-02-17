package com.nike.urbandictionary.responses

sealed class Responses<out T>
class Success<out T>(val data: T) : Responses<T>()
class Failure<out T>(val reason: String) : Responses<T>()
class EmptyResponse<out T> : Responses<T>()