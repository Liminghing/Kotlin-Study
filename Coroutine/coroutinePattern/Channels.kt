package com.jkweyu.coroutinepractice.coroutineEx

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * `runBlocking` 선언된 CoroutineScope
 */
fun main() = runBlocking {
    /*
     * - `val channel = Channel<Int>()`
     *
     *     정수형(`Int`) 값을 주고받을 수 있는 채널을 생성
     */
    val channel = Channel<Int>()
    /*
     * - `launch {…}`
     *
     *      새로운 코루틴을 시작
     *
     *     - `for(x in 1..5) channel.send(x * x)`
     *
     *         5번 루프를 돌면서 `hannel.send(x * x)`로 채널에 보내기
     *
     *         `send()`는 비동기적으로 데이터를 채널에 전달
     */
    launch {
        for(x in 1..5) channel.send(x * x)
    }
    /*
     * - `repeat(5){…}`
     *
     *     5번 반복하는 코드 블록을 실행
     *
     *     - `println(channel.receive())`
     *
     *         `channel.receive()`로 채널에서 값을 받아온다
     */
    repeat(5){
        delay(1000)
        println(channel.receive())
    }
    println("Done!")
}