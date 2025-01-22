package com.jkweyu.coroutinepractice.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * `runBlocking`은 메인 스레드에서 코루틴을 실행하도록 하는 블록
 *
 * → 코루틴이 완료될 때까지 메인 스레드가 종료되지 않음
 */
fun main() = runBlocking{
    /*
     * - `val job = launch {…}`
     *
     *     launch로 생성된 코루틴은 Job 객체를 반환 → Job 객체가 코루틴 관리
     *
     *     코루틴이 메인 스레드를 사용하므로 코루틴이 기존 메인스레드를 재사용하여 마치 다른 스레드를 사용하는 것처럼 동작
     */
    val job = launch {
        delay(500L)
        println(Thread.currentThread().name+ "111")
    }
    println(Thread.currentThread().name + "222")
    /*
     * - `job.cancel()`
     *
     *     Job 코루틴 객체의 예약을 취소하는 명령어
     */
    job.cancel()
    /*
     * - `job.join()`
     *
     *     코루틴이 취소되었기 때문에, `join()`은 코루틴이 종료될 때까지 기다리고 종료된다.
     */
    job.join()
}
