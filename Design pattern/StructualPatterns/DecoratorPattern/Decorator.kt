package com.jkweyu.designpatternstudy.StructualPatterns.DecoratorPattern

/**
 * - `CoffeeMachine` 가장 기본이 되는 인터페이스
 * - `AmateurCoffeeMachine` 기본 `CoffeeMachine` 를 상속받은 클래스
 * - `ProfessionalCoffeeMachine`
 *     - 기본 `CoffeeMachine` 를 매개변수로 받는다
 *     - `CoffeeMachine` 를 상속받는다
 *     - 전달 받은 `CoffeeMachine` 매개변수에 위임
 *     - `makeLatte()` 와 `makeCappuccino()` 라는 기능 확장
 * - `main()`
 *     - `AmateurCoffeeMachine` 객체 생성
 *     - `AmateurCoffeeMachine` 를 매개변수로 받는 `ProfessionalCoffeeMachine` 객체 생성
 *         - 최종적으로 `ProfessionalCoffeeMachine` 는 `AmateurCoffeeMachine` 에 위임
 */

class Milk(milliliters: Int)
class Coffee(milliliters: Int)

class Latte(milk: Milk, coffee: Coffee)
class Cappuccino(milk: Milk, coffee: Coffee)


// CoffeeMachine 기본 기능 정의
interface CoffeeMachine {
    val leftCoffeeMilliliters : Int
    val leftMilkMilliliters : Int
    fun makeCoffee(milliliters : Int) : Coffee {
        leftCoffeeMilliliters - milliliters
        return Coffee(milliliters)
    }
    fun makeMilk(milliliters: Int) : Milk {
        leftMilkMilliliters - milliliters
        return Milk(milliliters)
    }
}

// CoffeeMachine 인터페이스 구현
class AmateurCoffeeMachine(
    override val leftCoffeeMilliliters: Int,
    override val leftMilkMilliliters: Int
) : CoffeeMachine

class ProfessionalCoffeeMachine(coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine{
    fun makeLatte() = Latte(makeMilk(150),makeCoffee(150))
    fun makeCappuccino() = Cappuccino(makeMilk(100),makeCoffee(70))
}
fun main(){
    val coffeeMachine = AmateurCoffeeMachine(1000,1000)
    val professionalCoffeeMachine = ProfessionalCoffeeMachine(coffeeMachine)
}