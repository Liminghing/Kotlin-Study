package com.jkweyu.interoperability.CallJavaFromKotlin

import java.util.Scanner

class FromKotlin {
    /* 정적 변수,함수 호출
     * 자바 클래스명으로 접근후 message변수에 할당에 코틀린의 일반 변수 처럼 사용
     */
    fun callStaticFromJava(){
        var message = CallJava.message
        println("Java Message : $message")
        CallJava.add(4,5)
    }

    /* 코틀린에서 자바 컬렉션 호출
     * CallJava.getInt1List() 을 통해 접근후 list 변수에 할당(코틀린이 유형파악)
     */
    fun callGetIntListMethod(){
        val list = CallJava.getInt1List()
        list.add(4)
        for(element in list){
            println(element)
        }
    }

    /* 코틀린의 예약어
     * 코틀린에서 자바에서 선언한 코틀린 예약어 변수는 역따옴표(`)를 연산자를 이용
     */
    fun callReserveMethod(){
        CallJava.`is`()
        CallJava.`var`()

        var `var` = "Reserved keyword var"
        var `object` = "Reserved keyword object"
    }

    fun `in`(){

        println("I am in function")
    }

    fun `as`(){
        println("I am as function")
    }

    fun `object`(){
        println("I am object function")
    }

    fun inputFromKeyboard() {

        println("Enter Your name .... ")

        val scanner = Scanner(System.`in`)

        println("My name is ${scanner.nextLine()}")
    }

    /* 코틀린에서 자바 클래스
     * 코틀린의 shape 변수는 자바Shape 클래스 인스턴스이고 shape 를통해 자바의 Shape클래스의 프로퍼티 및 메서드에 접근 가능
     */
    fun callJavaClassInstance(){

        val shape = Shape(5,5,"Square")

        shape.shapeMessage()

        shape.height = 10

        println("${shape.shape} height = ${shape.height}")
    }

}