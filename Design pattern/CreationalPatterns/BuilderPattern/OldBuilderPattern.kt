package com.jkweyu.designpatternstudy.CreationalPatterns.BuilderPattern



class Meat
class Cheese
class Ketchup
class Bun


//.재료를 캡슐화한 버거 클래스
class Burger{
    private val meat : Meat
    private val cheese : Cheese
    private val ketchup : Ketchup
    private val bun : Bun

    private constructor(meat : Meat, cheese : Cheese, ketchup : Ketchup, bun : Bun){
        this.meat = meat
        this.cheese = cheese
        this.ketchup = ketchup
        this.bun = bun
    }
    // 생성자 (중첩된 생성자 사용)
    class Builder {
        private var meat : Meat = Meat()
        private var cheese : Cheese = Cheese()
        private var ketchup : Ketchup = Ketchup()
        private var Bun : Bun = Bun()
        fun setMeat(meat : Meat) : Builder {
            this.meat = meat
            return this
        }
        fun setCheese(cheese: Cheese) : Builder {
            this.cheese = cheese
            return this
        }
        fun setKetchup(ketchup: Ketchup) : Builder {
            this.ketchup = ketchup
            return this
        }
        fun setBun(bun: Bun) : Builder {
            this.Bun = bun
            return this
        }
        fun build() : Burger {
            return Burger(meat, cheese, ketchup, Bun)
        }
    }
}


fun main(){
    val burger : Burger = Burger.Builder()
        .setMeat(Meat())
        .setCheese(Cheese())
        .setKetchup(Ketchup())
        .setBun(Bun())
        .build()
}