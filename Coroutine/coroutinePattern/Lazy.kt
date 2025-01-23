package com.jkweyu.coroutinepractice.coroutineEx

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//**메인 스레드를 차단**하여 내부에서 코루틴들이 모두 완료될 때까지 대기
fun main() = runBlocking {
    println("코루틴 시작 (${Thread.currentThread().name})")
    /*
     * - `val job = launch(start = CoroutineStart.LAZY) {…}`
     *
     *     `CoroutineStart.LAZY` 를 통해 `start()` 호출 전까지 실행되지 않음
     *
     *     → `start()` 호출후에 `launch` 의 코루틴이 예약된다
     */
    val job = launch(start = CoroutineStart.LAZY) {
        println("지연된 작업 실행 (${Thread.currentThread().name})")
        delay(1000L) // 1초 대기
        println("지연된 작업 완료 (${Thread.currentThread().name})")
    }
    /*
     * - `val job2 = launch {…}`
     *
     *     `launch` 의 코루틴이 예약된다 (1번째 예약)
     */
    launch {
        println("일반 작업 실행 (${Thread.currentThread().name})")
        delay(1000L) // 1초 대기
        println("일반 작업 완료 (${Thread.currentThread().name})")
    }
    /*
     * - `job.start()`
     *
     *     `start()` 호출 `launch` 의 코루틴이 예약 (2번째 예약)
     */
    job.start()
    /*
     * - `job.join()`
     *
     *     job의 코루틴이 완료될때까지 예약된 코루틴 순서대로 실행
     *
     *     → `일반 블록 실행` 이후 `지연 블록 실행`
     */
    job.join()
}
