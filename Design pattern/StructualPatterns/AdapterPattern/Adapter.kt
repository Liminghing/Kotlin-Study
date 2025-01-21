package com.jkweyu.designpatternstudy.StructualPatterns.AdapterPattern

/**
 * - `Cat` 클래스는 `Meowable` 인터페이스를 객체로 가지는 클래스
 * - `Dog` 클래스는 `Barkable` 인터페이스를 객체로 가지는 클래스
 * - `Adapter` 는 `Meowable` 와 `Barkable` 를 둘다 상속받고 특정 인터페이스의 메서드를 재정의
 *     재정의를 통해 다른 인터페이스를 사용가능
 * - `main` 에서 `Cat` 객체에 `Adapter` 제공을 통해 재정의를 통해 다른 인터페이스를 사용가능화
 */

interface Barkable {
    fun bark(){
        println("bark")
    }
}
interface Meowable {
    fun meow(){
        println("meow")
    }
}

class Cat(private val meowable: Meowable){
    fun voice(){
        meowable.meow()
    }
}
class Dog(private val barkable: Barkable){
    fun voice(){
        barkable.bark()
    }
}

class Adapter : Barkable ,Meowable {
    override fun meow(){
        bark()
    }
}
fun main(){
    Cat(Adapter()).voice()
}