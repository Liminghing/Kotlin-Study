package com.jkweyu.designpatternstudy.StructualPatterns.ProxyPattern

/**
 * - `File` 인터페이스 : 기본 동작정의
 * - `MediaFile` 클래스 : 실제 작업을 수행하는 **원래 객체**
 * - `CacheMediaFile` 클래스 : **프록시**로서 `MediaFile` 을 대리역할
 *     - 대리인 역할을 하는 `CacheMediaFile` 에서 지연 초기화를 통해 `MediaFile` 객체 지연 생성
 *     - 필요시점에 `showContent()` 호출을 통해 `MediaFile` 객체 초기화
 */

interface File{
    fun showContent()
}
class MediaFile : File {
    override fun showContent() = println("showContent")
}

class CacheMediaFile : File {
    private val file by lazy { MediaFile() }
    override fun showContent() = file.showContent()
}
