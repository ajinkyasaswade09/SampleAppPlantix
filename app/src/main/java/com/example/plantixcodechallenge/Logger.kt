package com.example.plantixcodechallenge

import javax.inject.Inject

interface Logger {
    fun log(message: Any?)
}

class SystemLogger @Inject constructor() : Logger {
    override fun log(message: Any?) {
        println(message)
    }
}
