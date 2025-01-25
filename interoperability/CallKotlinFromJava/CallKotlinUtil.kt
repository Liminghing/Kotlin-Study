@file:JvmName("KotlinUtil") //코틀린 파일명을 KotlinUtil 로 지정
package com.jkweyu.interoperability.CallKotlinFromJava

fun addition (a: Int, b : Int){
    println("Result of $a + $b is ${a+b}")
}
//자바용 코틀린 함수의 이름을 addDouble 로 지정
@JvmName ("addDouble")
fun addition (a: Double, b : Double){

    println("Result of $a + $b is ${a+b}")

}

object Singleton {
    fun happy() {
        println("I am Happy")
    }
}
object SingletonWithAnontation {
    @JvmStatic fun excited() {
        println("I am very Excited")
    }
}
