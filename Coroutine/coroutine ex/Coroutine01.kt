package com.jkweyu.coroutinepractice.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(){
    /*
     * - **`GlobalScope.launch` 블록**
     *
     *     새 코루틴을 생성하고 실행을 예약하는 블록 → 비동기 처리를 위한 특별한 스레드 예약
     *
     *     `GlobalScope` : 어플 전체의 생명 주기에 걸쳐 실행되는 코루틴
     *
     *     `launch` : 선택한 코루틴을 실행하는 코루틴 빌더
     *
     *     - `Thread.currentThread().name`
     *
     *         스레드를 다루는 `Thread` 클래스의 `currentThread()`를 통해 현재 활성화된 스레드 정보를 가져옴
     */
    GlobalScope.launch {
        delay(500L)
        println(Thread.currentThread().name)
    }
    println(Thread.currentThread().name)
    /*
     * - `Thread.currentThread().join()`
     *
     *     현재 스레드(메인 스레드)가 종료될 때까지 대기 → 예약되어있는 코루틴 활성화
     */
    Thread.currentThread().join()
}
