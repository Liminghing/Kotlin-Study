package com.jkweyu.exception

class Todo(val name: String, val content: String)

class TodoAlreadyExistException(
    message: String? = null,
    cause: Throwable? = null,
    enableSuppression: Boolean = true,
    writableStackTrace: Boolean = true
) : Exception(message,cause,enableSuppression,writableStackTrace)

class TodoStorage {
    private val todos = HashMap<String, Todo>()
    operator fun get(name: String) = todos[name]
    operator fun set(name: String, todo: Todo) {
        if(todos.contains(name)){
            throw TodoAlreadyExistException()
        }else {
            todos[name] = todo
        }
    }
}
fun main(){
    val storage = TodoStorage()
    val todo = Todo("할일", "할일 내용")
    storage[todo.name] = todo
    storage[todo.name] = todo
}
