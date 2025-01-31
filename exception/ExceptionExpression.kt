package com.jkweyu.exception

fun loadValue() : Int = throw Exception()

fun main(){
    println(try { loadValue() } catch (e : Exception){ 4 })
}