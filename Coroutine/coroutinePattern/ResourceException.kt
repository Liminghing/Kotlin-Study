package com.jkweyu.coroutinepractice.coroutineEx

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileReader


fun main() = runBlocking {
    val job = launch {
        try {
            delay(10000)
        }
        /*
         * `launch` 에서 생성한 코루틴이 종료(예외)되더라도 항상 실행되는 블록
         *
         * 코루틴 종료시 `finally{…}` 블록 비동기작업 수행시 오류 발생 주의
         */
        finally {
            /*
             * `NonCancellable`는 코루틴의 취소가 불가능하게 만드는 컨텍스트
             *
             * → `finally` 블록 작업들이 코루틴이 취소되더라도 중단되지 않고 계속 실행
             */
            withContext(NonCancellable){
                /*
                 * `finally` 으로 설정되어 코루틴 종료,예외발생시 실행
                 */
                try {
                    println("start")
                    //코루틴 종료시 `finally{…}` 블록 비동기작업 수행시 오류 발생하기 때문에 withContext(NonCancellable)블록 내에서 실행
                    delay(1000)
                    println("end")
                } catch (exception : Exception){
                    exception.printStackTrace()
                }
            }
        }
    }
    delay(500)
    job.cancel()
    job.join()
}