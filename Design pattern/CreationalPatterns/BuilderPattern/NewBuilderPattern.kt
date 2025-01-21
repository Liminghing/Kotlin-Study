package com.jkweyu.designpatternstudy.CreationalPatterns.BuilderPattern


class Meat01
class Cheese01
class Ketchup01
class Bun01

//코틀린의 명명된 기본 인수 기능 사용
class Kotlinger(
    private val meat : Meat01 = Meat01(),
    private val cheese : Cheese01 = Cheese01(),
    private val ketchup : Ketchup01 = Ketchup01(),
    private val bun : Bun01 = Bun01()
)

fun main(){
    val kotlinger : Kotlinger = Kotlinger(
        meat = Meat01(),
        cheese = Cheese01(),
        ketchup = Ketchup01(),
        bun = Bun01()
    )
}