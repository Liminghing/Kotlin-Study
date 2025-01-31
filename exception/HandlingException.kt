package com.jkweyu.exception

import java.io.FileInputStream
import java.io.IOException

// throw 키워드를 사용하는 방식으로 예외 처리

fun divide01(a: Int, b: Int): Int {
    if (b == 0) {
        throw IllegalArgumentException("divide01 예외 발생 : 0으로 나눌 수 없습니다.")
    }
    return a / b
}
// @Throws 어노테이션을 사용해 자바와의 상호 운용성 고려

@Throws
fun divide02(a: Int, b: Int): Int {
    if (b == 0) {
        throw IllegalArgumentException("divide02 예외 발생 : 0으로 나눌 수 없습니다.")
    }
    return a / b
}

// try-catch 블록을 사용해 예외가 발생했을 때 적절한 처리

fun divide03(a: Int, b: Int) {
    try {
        val result = divide01(a, b)
        println("결과: $result")
    } catch (e: IllegalArgumentException) {
        println("divide03 예외 발생: {${e.message}}")
    }
}

// 여러 유형의 예외를 처리하는 방법

fun divide04() {
    try {
        FileInputStream("invalid/path")
    } catch (e: ArithmeticException) {
        println("ArithmeticException ${e::class.java.name} : ${e.message}")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("ArrayIndexOutOfBoundsException ${e::class.java.name} : ${e.message}")
    } catch (e: Exception) {
        println("Exception ${e::class.java.name} : ${e.message}")
    } catch (e: IOException) {
        println("IOException ${e::class.java.name} : ${e.message}")
    }
}

fun main(){
//    println(divide01(10, 0))
//    println(divide02(10, 0))
//    divide03(10, 0)
    divide04()
}