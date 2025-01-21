package com.jkweyu.designpatternstudy.CreationalPatterns.SingletonPattern

class User private constructor(
    var name : String? = null,
    var age : Int? = null
) {
    companion object{
        private val user by lazy { User() }
        fun getInstance() : User = user
    }
}
fun main(){
    with(User.getInstance()){
        name = "messi"
        age = 35
    }
}