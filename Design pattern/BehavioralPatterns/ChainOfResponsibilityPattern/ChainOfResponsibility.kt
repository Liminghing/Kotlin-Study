package com.jkweyu.designpatternstudy.BehavioralPatterns.ChainOfResponsibilityPattern

/*
 *- **`Request` 인터페이스**
 *
 *     모든 요청 클래스들이 따르게 되는 규칙을 정의
 */
interface Request
/*
 * - **요청 클래스(`GETRequest`, `POSTRequest`, `PUTRequest`, `DELETERequest`)**
 *
 *     요청 메소드(GET, POST, PUT, DELETE)를 나타내는 클래스
 */
class GETRequest : Request

class POSTRequest : Request

class PUTRequest : Request

class DELETERequest : Request

/*
 * - **`RequestHandler` 인터페이스**
 *
 *     요청을 처리하는 각 클래스가 가져야 할 공통 메서드를 정의
 *
 *     - `val nextHandler: RequestHandler?`
 *
 *         다음 처리기를 연결하기 위한 변수
 *
 *     - `fun handle(request: Request)`
 *
 *         요청을 처리하는 메서드로서 각 요청(`GETRequest`, `POSTRequest`, `PUTRequest`, `DELETERequest`)을 변수로 받는다
 */
interface RequestHandler{
    val nextHandler : RequestHandler?
    fun handle(request : Request)
}

/*
 * - **`GETRequestHandler` 클래스 : `RequestHandler`**
 *     - `override val nextHandler = POSTRequestHandler()`
 *
 *         다음 처리기로 `POSTRequestHandler` 연결
 *
 *     - `handle` 재정의
 *
 *         `handle` 에 받은 `request` 형태를 조건검사하여
 *
 *         참 : `GETRequest`인 경우 해당 요청을 처리
 *
 *         거짓 : `nextHandler` 을 통해 다음 `POSTRequestHandler` 로 요청넘기기
 */
class GETRequestHandler : RequestHandler {
    override val nextHandler = POSTRequestHandler()

    override fun handle(request: Request) {
        if (request is GETRequest){
            println("Handle GET request...")
        }
        else{
            nextHandler.handle(request)
        }
    }
}

/*
 * - **`POSTRequestHandler` 클래스 : `RequestHandler`**
 *     - `override val nextHandler = PUTRequestHandler()`
 *
 *         다음 처리기로 `PUTRequestHandler` 연결
 *
 *     - `handle` 재정의
 *
 *         `handle` 에 받은 `request` 형태를 조건검사하여
 *
 *         참 : `POSTRequest`인 경우 해당 요청을 처리
 *
 *         거짓 : `nextHandler` 을 통해 다음 `PUTRequestHandler` 로 요청넘기기
 */
class POSTRequestHandler : RequestHandler {
    override val nextHandler = PUTRequestHandler()

    override fun handle(request: Request) {
        if (request is POSTRequest){
            println("Handle POST request...")
        }
        else{
            nextHandler.handle(request)
        }
    }
}

/*
 * - **`PUTRequestHandler` 클래스 : `RequestHandler`**
 *     - `override val nextHandler = DELETERequestHandler()`
 *
 *         다음 처리기로 `DELETERequestHandler` 연결
 *
 *     - `handle` 재정의
 *
 *         `handle` 에 받은 `request` 형태를 조건검사하여
 *
 *         참 : `PUTRequest`인 경우 해당 요청을 처리
 */
class PUTRequestHandler : RequestHandler {
    override val nextHandler = DELETERequestHandler()

    override fun handle(request: Request) {
        if (request is PUTRequest){
            println("Handle PUT request...")
        }
        else{
            nextHandler.handle(request)
        }
    }
}

/*
 * - **`DELETERequestHandler` 클래스 : `RequestHandler`**
 *     - `override val nextHandler? = null`
 *
 *         다음 처리기에 null 처리
 *
 *     - `handle` 재정의
 *
 *         `handle` 에 받은 `request` 형태를 조건검사하여
 *
 *         참 : `DELETERequest`인 경우 해당 요청을 처리
 */
class DELETERequestHandler : RequestHandler {
    override val nextHandler: RequestHandler? = null

    override fun handle(request: Request) {
        if (request is DELETERequest){
            println("Handle DELETE request...")
        }
    }
}

/*
 * - **`main()`**
 *     - `GETRequestHandler` 객체를 생성
 *         - `apply.{…} 블록`
 *             - `handle(GETRequest())`
 *
 *                 `GETRequest`가 들어오면, `GETRequestHandler`는 이 요청을 **자기 자신이 처리**
 *
 *             - `handle(DELETERequest())`
 *
 *                 `DELETERequest`가 들어오면, `GETRequestHandler` → `POSTRequestHandler` → `PUTRequestHandler` → `DELETERequestHandler` 에서 `DELETERequest`를 처리
 */
fun main(){
    GETRequestHandler().apply {
        handle(GETRequest())
        handle(DELETERequest())
    }
}

