package com.jkweyu.coroutinepractice.coroutineEx

import kotlinx.coroutines.*

fun main() {
    /*
     * 코루틴 Job 객체를 담는 리스트
     */
    val job = arrayListOf<Job>()
    /*
     * - **`GlobalScope.launch {…}` 버전 1 추가**
     *
     *     `GlobalScope.launch` 를 사용하는 코루틴으로 설정후 코루틴 예약
     *
     *     - `println("Thread: ${Thread.currentThread().name}")`
     *
     *         현재 코루틴이 실행되는 스레드명 출력
     *
     *     - `repeat(500) {…}`
     *
     *         `print("1")` 을 500번 출력
     */
    job += GlobalScope.launch {
        println("Thread: ${Thread.currentThread().name}")
        repeat(500) {
            print("1")
        }
        println()
    }
    /*
     * - **`GlobalScope.launch {…}` 버전 2 추가**
     *
     *     `GlobalScope.launch` 를 사용하는 코루틴으로 설정후 코루틴 예약
     *
     *     - `println("Thread: ${Thread.currentThread().name}")`
     *
     *         현재 코루틴이 실행되는 스레드명 출력
     *
     *     - `repeat(500) {…}`
     *
     *         `print("2")` 을 500번 출력
     */
    job += GlobalScope.launch {
        println("Thread: ${Thread.currentThread().name}")
        repeat(500) {
            print("2")
        }
        println()
    }
    /*
     * - **`runBlocking {…}` 블럭**
     *
     *     자식 코루틴들이 완료될 때까지 메인 스레드가 종료되지 않음
     *
     *     - `job.forEach {…}` 블럭
     *
     *         **`job` 리스트의 각 요소를 `join()` 을 통해 코루틴 종료때까지 대기**
     *
     *         → ver1 실행 → ver1 실행중 + ver2 실행 → ver1 실행중 + ver2 실행중 → 완
     */
    runBlocking {
        println("Wating... (${Thread.currentThread().name})")
        job.forEach {
            it.join()
        }
    }
}