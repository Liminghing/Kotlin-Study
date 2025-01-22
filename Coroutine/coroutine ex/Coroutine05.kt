package com.jkweyu.coroutinepractice.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * main 블록을 `runBlocking` 으로 선언했기때문에 CoroutineScope
 */
fun main() = runBlocking {
    /*
     * - `launch {…}`
     *
     *     `launch` 빌더를 통해 코루틴 빌드 후 코루틴 예약1
     */
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
    /*
     * main 스코프 내에 `coroutineScope` 스코프 생성 → 자신의 스코프내 자식 종료를 대기
     */
    coroutineScope {
        /*
         * - `launch {…}`
         *
         *     `launch` 빌더를 통해 코루틴 빌드 후 코루틴 예약2
         */
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        /*
         * 내부 출력 실행
         */
        println("Task from coroutine scope")
    }
    println("Coroutine scope is over")
}