package com.jkweyu.designpatternstudy.BehavioralPatterns.StatePattern

/*
 * - **`AuthorizationState` 클래스(Sealed Class)**
 *
 *     Sealed Class로 선언해 동일 파일내에서만 상속가능한 상태를 나타내는 기본 클래스
 */
sealed class AuthorizationState

/*
 * - **각 상태 클래스들(`SignedUpState`,`ProfileCompledtedState`,`UnauthorizedState`)**
 *     - **`SignedUpState`** : ****가입 완료 상태를 나타내는 클래스
 *     - **`ProfileCompledtedState`** : 프로필 작성 완료 상태를 나타내는 클래스
 *     - **`UnauthorizedState`** : 인증되지 않은 상태를 나타내는 클래스
 */
class SignedUpState: AuthorizationState()

class ProfileCompledtedState: AuthorizationState()

class UnauthorizedState: AuthorizationState()

/*
 * - **`AuthorizationContext` 클래스**
 *
 *     **현재 인증 상태**를 관리하는 클래스
 *
 *     - `var state: AuthorizationState = UnauthorizedState()`
 *
 *         `UnauthorizedState` 로 초기화된 `AuthorizationState` 타입의 상태 프로퍼티
 *
 *     - `signUp()`
 *
 *         회원가입 완료시 상태 프로퍼티(`state`)를 `ProfileCompledtedState` 로 변경
 *
 *     - `completeProfile()`
 *
 *         프로필 작성 완료시 상태 프로퍼티(`state`)를 `ProfileCompledtedState` 로 변경
 *
 *     - `display()`
 *
 *         현재 상태 타입에 따라 다른 메시지를 출력
 *
 *     - `toString()`
 *
 *         기존 `toString()` 을 재정의 하여 상태 프로퍼티(`state`)의 상태타입을 출력하는 함수로 재정의 → 객체의 문자열 표현을 반환하는 함수로 `AuthorizationContext`객체 사용시 호출
 */
class AuthorizationContext {
    var state: AuthorizationState = UnauthorizedState()

    fun signUp(){
        state = ProfileCompledtedState()
    }

    fun completeProfile(){
        state = ProfileCompledtedState()
    }

    fun display() = when(state){
        is UnauthorizedState -> println("UnauthorizedState")
        is SignedUpState -> println("SignedUpState")
        is ProfileCompledtedState -> println("ProfileCompledtedState")
    }

    override fun toString(): String {
        return "AuthorizationContext(state=$state)"
    }
}

/*
 * - **`main()`**
 *     - `val context = AuthorizationContext()`
 *
 *         `AuthorizationContext` 객체 생성 → 현재 인증상태(`state`)를 `UnauthorizedState` 로 설정
 *
 *     - `context.display()`
 *
 *         현재 인증상태(`state`)를  출력 → `UnauthorizedState`
 *
 *     - `context.signUp()`
 *
 *         현재 인증상태(`state`)를  `ProfileCompledtedState` 로 변경
 *
 *     - `context.completeProfile()`
 *
 *         현재 인증상태(`state`)를  `ProfileCompledtedState` 로 변경
 *
 *     - `println(context)`
 *
 *         `context`를 출력하면 `toString()` 함수가 호출되어 상태 출려
 */
fun main(){
    val context = AuthorizationContext()
    context.display()
    context.signUp()
    println(context)
    context.display()
    context.completeProfile()
    println(context)
}