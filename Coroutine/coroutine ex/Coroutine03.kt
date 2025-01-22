package com.jkweyu.coroutinepractice.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    val job = launch {
        delay(500L)
        println("Coroutine")
    }
    println("Hello")
    job.join()
}
