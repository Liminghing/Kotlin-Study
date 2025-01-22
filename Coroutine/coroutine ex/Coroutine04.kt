package com.jkweyu.coroutinepractice.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val parentJob = Job()
    (0..10_000).forEach {
        CoroutineScope(parentJob).launch {
            println("Thread name : ${Thread.currentThread().name}")
        }
    }
    parentJob.children.forEach { it.join() }
}