package com.jkweyu.designpatternstudy.BehavioralPatterns.ObserverPattern

import kotlin.properties.Delegates

/*- **`TextChangeListener`**
 *     `typealias` 을 통해 `(text : String?) -> Unit` 형태의 함수를 `TextChangeListener` 로 명명
 */
typealias TextChangeListener = (text : String?) -> Unit

/*- **`TextInput01` 클래스**
 *     - `var text : String? = null`
 *
 *         `text` 변수 선언
 *
 *         - `set(value) {…}`
 *
 *             `text` 변수의 값이 설정될때 실행되는 블록
 *
 *             - `field = value`
 *
 *                 입력 받은 값을 블록의 변수(`text`)에 저장
 *
 *             - `textChangeListener?.invoke(value)`
 *
 *                 `textChangeListener` 에 `value` 값을 전달후 `textChangeListener` 실행
 *
 *     - `var textChangeListener : TextChangeListener? = null`
 *
 *         `TextChangeListener` 타입의 함수변수를 담을 공간
 */
class TextInput01 {
    var text : String? = null
        set(value) {
            field = value
            textChangeListener?.invoke(value)
        }
    var textChangeListener : TextChangeListener? = null
}

/*
 * - **`TextInput02` 클래스**
 *     - `var text by Delegates.observable<String?>(null)`
 *
 *         `text` 변수를 `Delegates.observable` 을 통해 프로퍼티 위임
 *
 *         **`Delegates.observable`** : 특정 변수가 바뀔 때, 자동으로 특정 동작을 실행
 *
 *         - `{…,newValue …} 블록`
 *
 *             `Delegates.observable`이 제공하는 블록으로 `text` 값 변경시 호출
 *
 *             - `textChangeListener?.invoke(newValue)`
 *
 *                 `textChangeListener` 에 `newValue` 값을 전달후 `textChangeListener` 실행
 *
 *     - `var textChangeListener : TextChangeListener? = null`
 *
 *         `TextChangeListener` 타입의 함수변수를 담을 공간
 */
class TextInput02 {
    var text by Delegates.observable<String?>(null) { _, _, newValue ->
        textChangeListener?.invoke(newValue)
    }
    var textChangeListener : TextChangeListener? = null
}

/*
 * - **`main()`**
 *     - `val textInput01 = TextInput01()` 변수
 *
 *         `TextInput01` 객체 생성
 *
 *         - `.apply {…}` 블록
 *
 *             `textInput01` 객체에 적용할 옵션
 *
 *             - `this.textChangeListener = {println(it)}`
 *
 *                 `textInput01` 객체의 `textChangeListener` 에 `println(it)` 라는 함수 대입
 *
 *     - `textInput01.text = "hello"`
 *
 *         `textInput01` 객체의 `text` 값을 할당 → `set(value) {…}` 동작
 *
 *         → textChangeListener 에 값 전달후 실행
 *
 *
 *     - `val textInput02 = TextInput02()` 변수
 *
 *         `TextInput02` 객체 생성
 *
 *         - `.apply {…}` 블록
 *
 *             `textInput02` 객체에 적용할 옵션
 *
 *             - `this.textChangeListener = {println(it)}`
 *
 *                 `textInput02` 객체의 `textChangeListener` 에 `println(it)` 라는 함수 대입
 *
 *     - `textInput02.text = "hello"`
 *
 *         `textInput02` 객체의 `text` 값을 할당 → `{…,newValue …} 블록` 동작
 *
 *         → textChangeListener 에 값 전달후 실행
 */
fun main(){
    val textInput01 = TextInput01().apply {
        this.textChangeListener = {println(it)}
    }
    textInput01.text = "hello"

    val textInput02 = TextInput02().apply {
        this.textChangeListener = {println(it)}
    }
    textInput02.text = "hello"
}