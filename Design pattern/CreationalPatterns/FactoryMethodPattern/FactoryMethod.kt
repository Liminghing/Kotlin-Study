package com.jkweyu.designpatternstudy.CreationalPatterns.FactoryMethodPattern

import com.jkweyu.designpatternstudy.CreationalPatterns.SingletonPattern.User

interface Car

class Mercedes : Car
class BMW : Car
class Audi : Car
class Hyundai : Car
class Tesla : Car

enum class Brand{
    MERCEDES, BMW, AUDI, HYUNDAI, TESLA
}


class CarFactory {
    fun createCar(brand : Brand) : Car{
        return when(brand){
            Brand.MERCEDES -> Mercedes()
            Brand.BMW -> BMW()
            Brand.AUDI -> Audi()
            Brand.HYUNDAI -> Hyundai()
            Brand.TESLA -> Tesla()
        }
    }
}

fun main(){
    val mercedes = CarFactory().createCar(Brand.MERCEDES)
}