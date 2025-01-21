package com.jkweyu.designpatternstudy.CreationalPatterns.BuilderPattern


class TextView{
    var text : String = ""
    var Color : String = "#000000"
}
class ImageView{
    var Color : String = "#000000"
}

class Window(init : Window.() -> Unit) {
    private var header : TextView? = null
    private var footer : TextView? = null

    init {
        init()
    }

    fun header(init : TextView.() -> Unit) {
        this.header = TextView().apply {init()}
    }

    fun footer(init : TextView.() -> Unit) {
        this.footer = TextView().apply {init()}
    }

    fun window(init : Window.() -> Unit): Window {
        return Window(init)
    }

}
fun main(){
//    var myTxt = TextView()
//    myTxt.text = "ddd"
//    myTxt.Color = "#000000"

    var myImg = ImageView()
    myImg.Color = "#000000"

    var myWindow : Window = Window {
        header {
            text = "Header"
            Color = "#000000"
        }
        footer {
            myImg
        }
    }
}