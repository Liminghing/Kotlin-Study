package com.jkweyu.designpatternstudy.CreationalPatterns.AbstractFactoryPattern



interface Car

class Mercedes : Car
class E220 : Car
class E300 : Car

class BMW : Car
class X5 : Car
class X6 : Car

enum class Brand{
    MERCEDES, BMW
}

class FactoryProducer{
    fun produceFactory(brand : Brand) : Factory = when(brand){
        Brand.MERCEDES -> MercedesFactory()
        Brand.BMW -> BMWFactory()
    }
}

interface Factory {
    fun createCar(model : Model) : Car
}

class MercedesFactory : Factory {
    override fun createCar(model: Model): Car = when(model){
        MercedesModel.E220 -> E220()
        MercedesModel.E300 -> E300()
        else -> TODO()
    }
}

class BMWFactory : Factory {
    override fun createCar(model: Model): Car = when(model){
        BMWModel.X5 -> X5()
        BMWModel.X6 -> X6()
        else -> TODO()
    }
}

interface Model

enum class MercedesModel : Model {
    E220,
    E300
}
enum class BMWModel : Model {
    X5,
    X6
}
fun main(){
    val e220 = FactoryProducer().produceFactory(Brand.MERCEDES).createCar(MercedesModel.E220)
}