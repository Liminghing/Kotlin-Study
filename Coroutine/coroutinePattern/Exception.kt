package com.jkweyu.coroutinepractice.coroutineEx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/*
 * - **`main()` 함수**
 *
 *     `runBlocking` 으로 선언한 CoroutineScope
 *
 *     - `launch {…}`
 *
 *         새로운 코루틴을 선언(예약) - 비동기적
 *
 *         - `val result = try{…} catch{…}`
 *
 *             `try{…}` 에서 실행한 코드가 예외 발생시 `catch{…}` 실행 후 `result` 에 담기
 *
 *             - `try{…}` 블록
 *
 *                 `calculateValue()` 함수 실행후 반환
 *
 *             - `catch{…}` 블록
 *
 *                 `defaultValue` 1을 반환
 */
fun main() = runBlocking {
//    launch {
//        val result = try{
//            calculateValue()
//        } catch (exception : Exception){
//            defaultValue
//        }
//        println(result)
//    }
}

/*
 * - **`val defaultValue = 1`**
 *
 *     예외 발생시 반환되는 값
 */
val defaultValue = 1

/*
 * - **`calculateValue() 메서드`**
 *     - `suspend` 선언
 *
 *         일시 중단이 가능한 함수로 선언해 비동기 처리가 가능하도록 처리
 *
 *     - `withContext(Dispatchers.Default)` 반환값
 *
 *         `withContext` 를 사용해 `Dispatchers.Default` 스레드를 실행 하도록함
 *
 *         → `Dispatchers.Default` 스레드를 사용하는코루틴 블록의 결과를 반환
 *
 *     - `throw Exception()` 예외 던지기
 *
 *         예외 발생시 `Exception()` 함수를 던짐
 */
suspend fun calculateValue() : Int = withContext(Dispatchers.Default){
    throw Exception()
}