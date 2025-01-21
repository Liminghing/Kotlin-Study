package com.jkweyu.designpatternstudy.BehavioralPatterns.VisitorPattern

/*
 * - **`Car` 클래스**
 *
 *     `CarElement` 을 상속 받아 구현한 클래스
 *
 *     - `private val elements = arrayOf(Body(), Engine())`
 *
 *         `Body`와 `Engine`을 요소로 가지는 `elements` 배열 프로퍼티
 *
 *     - `accept` 메서드
 *
 *         `CarElement` 의 `accept` 을 재정의
 *
 *         → 모든 요소들을 돌면서 `accept` 메서드를 호출
 */
class Car : CarElement{
    private val elements = arrayOf(Body(),Engine())

    override fun accept(visitor : CarElementVisitor){
        elements.forEach {it.accept(visitor)}
    }
}

/*
 * - **`Body`클래스**
 *
 *     `CarElement` 을 상속 받아 구현한 클래스
 *
 *     - `accept` 메서드
 *
 *         `CarElement` 의 `accept` 을 재정의
 *
 *         → `CarElementVisitor` 타입으로 받은 `visitor` 변수의 `visit` 메서드를 호출
 */
class Body : CarElement{
    override fun accept(visitor: CarElementVisitor) = visitor.visit(this)
}

/*
 * - **`Engine` 클래스**
 *
 *     `CarElement` 을 상속 받아 구현한 클래스
 *
 *     - `accept` 메서드
 *
 *         `CarElement` 의 `accept` 을 재정의
 *
 *         → `CarElementVisitor` 타입으로 받은 `visitor` 변수의 `visit` 메서드를 호출
 */
class Engine : CarElement {
    override fun accept(visitor: CarElementVisitor) = visitor.visit(this)
}

/*
 * - **`CarElement` 인터페이스**
 *     - `accept` 메서드
 *
 *         `CarElementVisitor`라는 **방문자 객체**를 받는다
 */
interface CarElement {
    fun accept(visitor : CarElementVisitor)
}

/*
 * - **`CarElementVisitor` 인터페이스**
 *
 *     자동차의 각 요소를 방문할 수 있는 기능을 정의
 *
 *     - `fun visit(body: Body)`
 *
 *         `Body` 객체를 방문
 *
 *     - `fun visit(engine: Engine)`
 *
 *         `Engine` 객체를 방문
 */
interface CarElementVisitor{
    fun visit(body : Body)
    fun visit(engine : Engine)
}

/*
 * - **`CarElementDriverVisitor` 클래스**
 *
 *     `CarElementVisitor` 을 상속 받아 구현한 클래스
 *
 *     - `visit(body: Body)` 재정의
 *
 *         자동차의 `Body` 를 준비하는 적업 (`println("Prepare body...")`)
 *
 *     - `visit(engine: Engine)` 재정의
 *
 *         자동차의 `Engine` 를 준비하는 적업 (`println("Prepare engine...")`)
 */
class CarElementDriverVisitor : CarElementVisitor{
    override fun visit(body: Body) {
        println("Prepare body...")
    }
    override fun visit(engine: Engine) {
        println("Prepare engine...")
    }
}

/*
 * - **`main()`**
 *     - `val car = Car()`
 *
 *         `Car` 객체를 생성
 *
 *     - `car.accept(CarElementDriverVisitor())`
 *
 *         `CarElementDriverVisitor` 객체를 생성하여`car.accept(CarElementDriverVisitor())`로 자동차 객체에 방문자(Driver Visitor)를 전달
 */
fun main(){
    val car = Car()
    car.accept(CarElementDriverVisitor())
}