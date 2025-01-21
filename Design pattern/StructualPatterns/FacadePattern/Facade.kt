package com.jkweyu.designpatternstudy.StructualPatterns.FacadePattern

import java.util.prefs.Preferences

/**
 * - `User` 은 내무적으로 `Preferences`라는 복잡한 시스템
 * - `main()` 에서는 `User` 클래스를 사용해 **`Preferences`를 직접 다룰 필요 없이** 간단히 다룸 : **`User` 클래스를 통해 이 과정이 추상화**
 */

data class User(
    private val preferences: Preferences =
        Preferences.userRoot().node(User::class.java.simpleName),
    val id : Int = preferences.getInt(User::id.name,0),
    val firstName : String = preferences.get(User::firstName.name,""),
    val lastName : String = preferences.get(User::lastName.name,"")
){
    init {
        with(preferences){
            putInt(User::id.name,id)
            put(User::firstName.name,firstName)
            put(User::lastName.name,lastName)
        }
    }
}

fun main(){
    //User 클래스를 사용해 Preferences를 직접 다룰 필요 없이 간단히 다룸
    User(id = 1, firstName = "John", lastName = "Doe").apply {
        println(this)
    }
    println(User())
}