package com.jkweyu.designpatternstudy.BehavioralPatterns.CommandPattern

import java.util.LinkedList

/*
 * - **`User` 클래스**
 *
 *     사용자의 정보를 저장하는 데이터 클래스
 */
data class User(val firstName: String,val lastName: String)

/*
 * - **`UserCommand` 인터페이스**
 *
 *     `UserCommand` 의 규칙을 정의하는 인터페이스
 *
 *     - `val user : User`
 *
 *         `UserCommand` 를 수핼할 객체를 저장할 프로퍼티
 *
 *     - `fun execute()`
 *
 *         `UserCommand` 의 실질적인 기능부
 */
interface UserCommand {
    val user : User
    fun execute()
}

/*
 * - **`CreateUserCommand` 명령 클래스**
 *
 *     사용자객체를 생성 명령을 담당하는 클래스
 *
 *     - `(override val user: User)`
 *
 *         `UserCommand` 에서 정의한 `val user : User` 프로퍼티를 해당 클래스에서 사용(재정의)
 *
 *     - `execute()`
 *
 *         `UserCommand` 에서 정의한 `fun execute()` 을 재정의해 사용
 *
 *         → 데이터 추가등의 기능을 구현
 */
class CreateUserCommand(override val user: User) : UserCommand {
    override fun execute() {
        println("Creating...")
    }
}

/*
 * - **`DeleteUserCommand` 명령 클래스**
 *     - `(override val user: User)`
 *
 *         `UserCommand` 에서 정의한 `val user : User` 프로퍼티를 해당 클래스에서 사용(재정의)
 *
 *     - `execute()`
 *
 *         `UserCommand` 에서 정의한 `fun execute()` 을 재정의해 사용
 *
 *         → 데이터 삭제등의 기능을 구현
 */
class DeleteUserCommand(override val user: User) : UserCommand {
    override fun execute() {
        println("Deleting...")
    }
}

/*
 * - **`Executor` 클래스**
 *
 *     명령을 실행하는 담당하는 클래스
 *
 *     - `private val queue = LinkedList<UserCommand>()`
 *
 *         여러 명령 객체(`UserCommand`)를 저장하는 큐
 *
 *     - `addCommand()`
 *
 *         `UserCommand` 객체를 큐에 저장하는 함수
 *
 *     - `execute()`
 *
 *         큐의 모든 `UserCommand` 객체의 `execute()` 실행
 */
class Executor{
    private val queue = LinkedList<UserCommand>()
    fun addCommand(command: UserCommand){
        queue.add(command)
    }
    fun execute(){
        queue.forEach { it.execute() }
    }
}

/*
 * - **`main()`**
 *     - `val executor = Executor()`
 *
 *         명령을 실행하는 `Executor` 객체를 생성하여 명령을 실행할 준비
 *
 *     - `val user = User("John","Doe")`
 *
 *         사용자 정보 생성
 *
 *     - `executor.addCommand(CreateUserCommand(user))`
 *
 *         명령 실행 객체 `Executor` 를 통해 큐에 사용자 추가 명령 추가
 *
 *     - `executor.addCommand(DeleteUserCommand(user))`
 *
 *         명령 실행 객체 `Executor` 를 통해 큐에 사용자 제거 명령 추가
 *
 *     - `executor.execute()`
 *
 *         명령 실행 객체 `Executor` 의 모든 명령 수행
 */
fun main(){
    val executor = Executor()
    val user = User("John","Doe")
    executor.addCommand(CreateUserCommand(user))
    executor.addCommand(DeleteUserCommand(user))
    executor.execute()

}